package spring_advance.advance.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring_advance.advance.trace.strategy.code.strategy.ContextV1;
import spring_advance.advance.trace.strategy.code.strategy.Strategy;
import spring_advance.advance.trace.strategy.code.strategy.StrategyLogic1;
import spring_advance.advance.trace.strategy.code.strategy.StrategyLogic2;

/**
 * 선조립 후 실행
 */
@Slf4j
public class ContextV1Test {

    @Test
    public void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    /**
     * 전략 패턴 사용
     * */
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();   //전략 인테페이스를 구현한 클래스를 생성
        ContextV1 contextV1 = new ContextV1(strategyLogic1);    //위에서 생성한 전략을 넣어 컨택스트 생성
        contextV1.execute();                                    //컨텍스트에서 해당 전략을 사용하는 메서드 콜
        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }


    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {              //전략 인터페이스 구현 클래스를 익명클래스로 여기서 생성
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategyLogic1);     //v1과 동일하게 생성한 전략을 넣어 컨택스트 생성
        log.info("strategyLogic1={}",strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}",strategyLogic2.getClass());
        context2.execute();
    }

    @Test
    void strategyV3 () {
        ContextV1 context1 = new ContextV1(new Strategy() {     //익명클래스로 컨택스트에 익명클래로 생성한 전략을 바로 주입
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context2.execute();
    }
    @Test
    void strategyV4 () {
        //v3의 코드를 람다를 사용하여 변경
        ContextV1 contextXXX = new ContextV1(() -> {
            log.info("비지니스 로직1 실행");
        });
        //구현해야하는 인터페이스의 메서드가 1개일 경우 람다 치환 가능
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context2.execute();
    }

}
