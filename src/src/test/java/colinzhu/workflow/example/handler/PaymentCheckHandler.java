package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import io.vertx.core.Future;

import java.util.function.BiFunction;

public class PaymentCheckHandler implements BiFunction<Payment, Object, Future<Event>> {
    @Override
    public Future<Event> apply(Payment payment, Object request) {
        return Future.succeededFuture(new Event("BLOCKED"));
    }
}
