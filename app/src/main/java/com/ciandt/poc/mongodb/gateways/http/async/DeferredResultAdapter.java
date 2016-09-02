package com.ciandt.poc.mongodb.gateways.http.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Created by rodrigosd on 5/6/16.
 */
@Slf4j
public class DeferredResultAdapter {

    private DeferredResultAdapter(){}

    public static <T> DeferredResult<T> requestAsync(final TaskExecutor executor,
        final Callable<T> action) {
        final DeferredResult<T> result = new DeferredResult<>();

        final Runnable beforeCallable = () -> {
            try {
                T t = action.call();

                if (result.isSetOrExpired()) {
                    log.error("async request expired");
                    return;
                }

                result.setResult(t);
            } catch (final Exception ex) {
                result.setErrorResult(ex);
            }
        };

        executor.execute(beforeCallable);
        return result;
    }
}
