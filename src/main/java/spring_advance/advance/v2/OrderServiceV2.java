package spring_advance.advance.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_advance.advance.trace.TraceId;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.helloTrace.HelloTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(),itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status,e);
            throw e;
        }
    }
}
