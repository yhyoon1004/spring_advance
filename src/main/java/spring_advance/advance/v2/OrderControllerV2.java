package spring_advance.advance.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.helloTrace.HelloTraceV1;
import spring_advance.advance.trace.helloTrace.HelloTraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status,e);
            throw e; // 발생한 예외에 대한 로직을 수행하고 리턴하는 로직이 없기에 예러를 던져줘야한다.
        }
    }
}
