package spring_advance.advance.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;


/**
 * 전략을 파라미터로 전달 받는 방식
 * */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        strategy.call();    //로직실행 위임

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

}
