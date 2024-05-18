package spring_advance.advance.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_advance.advance.trace.TraceId;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.logtrace.LogTrace;
import spring_advance.advance.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;


    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");

    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
