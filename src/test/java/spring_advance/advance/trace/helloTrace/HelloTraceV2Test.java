package spring_advance.advance.trace.helloTrace;

import org.junit.jupiter.api.Test;
import spring_advance.advance.trace.TraceStatus;

class HelloTraceV2Test {


    @Test
    public void begin () throws Exception{
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception () throws Exception{
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}