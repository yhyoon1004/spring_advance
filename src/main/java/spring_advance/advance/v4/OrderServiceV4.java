package spring_advance.advance.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.logtrace.LogTrace;
import spring_advance.advance.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");

    }
}
