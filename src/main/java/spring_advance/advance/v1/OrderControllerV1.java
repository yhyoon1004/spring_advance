package spring_advance.advance.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.helloTrace.HelloTraceV1;
import spring_advance.advance.v0.OrderServiceV0;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status,e);
            throw e; // 발생한 예외에 대한 로직을 수행하고 리턴하는 로직이 없기에 예러를 던져줘야한다.
        }
    }
}
