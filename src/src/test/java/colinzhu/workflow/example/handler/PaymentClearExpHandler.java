package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentClearExpRequest;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class PaymentClearExpHandler implements BiFunction<Payment, PaymentClearExpRequest, Future<Event>> {
    @Override
    public Future<Event> apply(Payment payment, PaymentClearExpRequest request) {
        log.info("Clear exp comment: " + request.getComment());
        return Future.succeededFuture(new Event("BLOCKED"));
    }
}
