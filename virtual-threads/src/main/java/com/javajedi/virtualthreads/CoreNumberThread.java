package com.javajedi.virtualthreads;

import com.javajedi.ThreadUtil;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CoreNumberThread {

    static int numberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    static void viewCarrierThreadPoolSize() {
        final var factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            IntStream.range(0, numberOfCores() + 1).forEach(i ->
                    executor.submit(() -> {
                        ThreadUtil.log("Hello, I am virtual thread number " + i);
                        ThreadUtil.sleep(Duration.ofSeconds(1L));
                            }
                    )
            );
        }
    }

    public static void main(String[] args) {
        viewCarrierThreadPoolSize();
    }

}
