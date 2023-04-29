package com.javajedi.introduction;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static com.javajedi.ThreadUtil.*;

public class VirtualThread {

    static Thread virtualThread(String name, Runnable task) {
        return Thread.ofVirtual()
                .name(name)
                .start(task);
    }

    static Thread bathTime() {
        return virtualThread("Bath-time", () -> {
            log("I am going to bath");
            sleep(Duration.ofMillis(500L));
            log("I am done with the bath");
        });
    }

    static Thread boilingWater() {
        return virtualThread("BoilWater", () -> {
            log("I am going to boil water");
            sleep(Duration.ofSeconds(1));
            log("I am done with boiling water");
        });
    }

    /*
    * Old way of creating and running threads.
    * */
    static void concurrentMorningRoutine() throws InterruptedException {
        var bath = bathTime();
        var boiling = boilingWater();
        bath.join();
        boiling.join();
    }

    /*
    * Threads created using ExecutorsService don't have a name, at times this plays a crucial role
    * when debugging application. Hence, it is always preferable to specify the thread name
    *
    * */
    static void concurrentRoutineWithExecutors() throws ExecutionException, InterruptedException {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var bathTime = executor.submit(
                    () -> {
                        log("I am going to boil water");
                        sleep(Duration.ofSeconds(1));
                        log("I am done with boiling water");
                    }
            );
            var boilingTime = executor.submit(
                    () -> {
                        log("I am going to boil water");
                        sleep(Duration.ofSeconds(1));
                        log("I am done with boiling water");
                    }
            );
            bathTime.get();
            boilingTime.get();
        }
    }

    static void concurrentMorningUsingExecutorsWithName() throws ExecutionException, InterruptedException {
        final ThreadFactory threadFactory = Thread.ofVirtual().name("routine", 0).factory();
        try(var executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
            var bathTime = executor.submit(
                    () -> {
                        log("I am going to boil water");
                        sleep(Duration.ofSeconds(1));
                        log("I am done with boiling water");
                    }
            );
            var boilingTime = executor.submit(
                    () -> {
                        log("I am going to boil water");
                        sleep(Duration.ofSeconds(1));
                        log("I am done with boiling water");
                    }
            );
            bathTime.get();
            boilingTime.get();
        }
    }

    public static void main(String[] args) throws Exception {
//        concurrentMorningRoutine();
//        concurrentRoutineWithExecutors();
        concurrentMorningUsingExecutorsWithName();
    }

}
