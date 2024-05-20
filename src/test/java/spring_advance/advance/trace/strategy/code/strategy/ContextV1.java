package spring_advance.advance.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * 필드에 전략을 보관하는 방식*/
@Slf4j
public class ContextV1 {
    private Strategy strategy;  //전략을 주입받음

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        strategy.call();    //로직실행 위임

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

}
