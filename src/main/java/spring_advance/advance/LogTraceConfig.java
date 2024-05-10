package spring_advance.advance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_advance.advance.trace.logtrace.FieldLogTrace;
import spring_advance.advance.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
