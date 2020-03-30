package com.trickyjava.how.eventtracker.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class AsyncExecutorService {


    @Async("taskExecutor")
    public void execute(Supplier<Boolean> supplier) {
        supplier.get();
    }

    @Async("taskExecutor")
    public <T, R> CompletableFuture<R> execute(Function<T, R> function, T input) {
        return CompletableFuture.completedFuture(function.apply(input));
    }
}
