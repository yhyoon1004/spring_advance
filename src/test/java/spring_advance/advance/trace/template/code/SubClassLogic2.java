package spring_advance.advance.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate{
    @Override
    protected void call() {
        log.info("비지니스 로직 2 실행");
    }
}
