package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentCancelRequest;
import io.vertx.core.Future;

import java.util.function.BiFunction;

public class PaymentCancelHandler implements BiFunction<Payment, PaymentCancelRequest, Future<Event>> {
    @Override
    public Future<Event> apply(Payment payment, PaymentCancelRequest request) {
        return Future.succeededFuture(new Event("CANCELLED"));
    }
}
