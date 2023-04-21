package colinzhu.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Slf4j
public class WorkflowEngine<T> {
    ObjectMapper objectMapper = new ObjectMapper();
    private final List<Rule<T>> rules;

    public WorkflowEngine(String ruleJson) {
        try {
            rules = objectMapper.readValue(ruleJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Number of rules: " + rules.size());
        for (Rule<T> rule : rules) {
            try {
                String className = rule.getHandlerClassName();
                BiFunction<T, Object, Future<Event>> handler = (BiFunction<T, Object, Future<Event>>) Class.forName(className).getDeclaredConstructor().newInstance();
                rule.setHandler(handler);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void process(Event event, T processable) {
        Optional<Rule<T>> optionalRule = rules.stream().filter(rule -> event.getName().equals(rule.getEventName())).findFirst();
        Future<Event> nextEvent;
        if (optionalRule.isPresent()) {
            BiFunction<T, Object, Future<Event>> handler = optionalRule.get().getHandler();
            log.info("Rule found for event: " + event + " handler: " + handler);
            nextEvent = handler.apply(processable, event.getRequest());
            nextEvent.onSuccess(evt -> {
                log.info("Next event: " + evt);
                process(evt, processable);
            });
        } else {
            log.info("No rule found for event: " + event);
        }

    }
}
