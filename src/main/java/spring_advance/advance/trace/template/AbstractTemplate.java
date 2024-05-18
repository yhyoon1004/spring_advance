package spring_advance.advance.trace.template;

import spring_advance.advance.trace.TraceStatus;
import spring_advance.advance.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace logTrace;

    protected AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T result = call();
            logTrace.end(status);
            return result; 

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    public abstract T call();

}
