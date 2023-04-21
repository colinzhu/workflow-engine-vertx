package colinzhu.workflow;

import io.vertx.core.Future;
import lombok.Data;

import java.util.function.BiFunction;

@Data
public class Rule<T> {
    private String eventName;
    private String handlerClassName;
    private BiFunction<T, Object, Future<Event>> handler;
}
