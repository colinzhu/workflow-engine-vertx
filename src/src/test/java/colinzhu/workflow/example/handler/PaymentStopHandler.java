package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentStopRequest;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class PaymentStopHandler implements BiFunction<Payment, PaymentStopRequest, Future<Event>> {
    @Override
    public Future<Event> apply(Payment payment, PaymentStopRequest request) {
        log.info("Stop payment comment: " + request.getComment());
        return Future.succeededFuture(new Event("STOPPED"));
    }
}
