package spring_advance.advance.v5;

import org.springframework.stereotype.Service;
import spring_advance.advance.trace.callback.TraceCallback;
import spring_advance.advance.trace.callback.TraceTemplate;
import spring_advance.advance.trace.logtrace.LogTrace;
import spring_advance.advance.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });

    }
}
