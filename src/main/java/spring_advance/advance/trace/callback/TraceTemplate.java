package spring_advance.advance.trace.callback;

import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace logTrace;

    public TraceTemplate(LogTrace trace) {
        this.logTrace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T result = callback.call();//콜백을 통한 call
            logTrace.end(status);
            return result;

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
