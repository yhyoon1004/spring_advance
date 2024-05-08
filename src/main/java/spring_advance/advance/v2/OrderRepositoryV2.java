package spring_advance.advance.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_advance.advance.trace.TraceId;
import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.helloTrace.HelloTraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;
    public void save(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status,e);
            throw e;
        }

    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
