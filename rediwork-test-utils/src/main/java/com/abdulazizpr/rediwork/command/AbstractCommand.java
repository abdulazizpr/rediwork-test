package com.abdulazizpr.rediwork.command;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import com.abdulazizpr.rediwork.command.execption.CommandValidationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SuppressWarnings("unchecked")
public abstract class AbstractCommand<RESPONSE, REQUEST extends CommandRequest>
        implements Command<RESPONSE, REQUEST>, ApplicationContextAware, InitializingBean {

    protected Validator validator;

    protected ApplicationContext applicationContext;

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        this.validator = applicationContext.getBean(Validator.class);
    }

    @Override
    public Mono<RESPONSE> execute(REQUEST request) {
        Set<ConstraintViolation<REQUEST>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty()) {
            return doExecute(request);
        } else {
            return Mono.error(new CommandValidationException(constraintViolations));
        }
    }

    public abstract Mono<RESPONSE> doExecute(REQUEST request);
}