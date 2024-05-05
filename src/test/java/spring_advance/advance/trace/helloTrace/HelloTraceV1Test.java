package spring_advance.advance.trace.helloTrace;

import org.junit.jupiter.api.Test;
import spring_advance.advance.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {


    @Test
    public void begin () throws Exception{
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    public void begin_exception () throws Exception{
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}