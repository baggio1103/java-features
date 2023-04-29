package com.javajedi.virtualthreads;

import java.time.Duration;

import static com.javajedi.ThreadUtil.*;

/**
 * Virtual Thread are mounted to OS / Platform threads when they are executed,
 * Whenever there is a blocking io operation in code, Virtual Thread release OS / Platform thread
 * and rescheduled for execution.
 * */
public class ErrorCooperativeScheduling {

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
        System.setProperty("jdk.virtualThreadScheduler.minRunnable", "1");
    }

    /**
     * Since working hard never reaches the blocking operation,
     * it never yields control to another VT
     * */
    static Thread workingHard() {
        return virtualThread("Working hard", () -> {
            log("I'm working hard");
            while (alwaysTrue()) {
                // Do nothing
            }
            sleep(Duration.ofMillis(100L));
            log("I'm done with working hard");
        });
    }

    static Thread takeABreak() {
        return virtualThread("Take a break", () -> {
            log("I'm going to take a break");
            sleep(Duration.ofSeconds(1L));
            log("I'm done with the break");
        });
    }

    static void workingHardRoutine() throws InterruptedException {
        var workingHard = workingHard();
        var takeABreak = takeABreak();
        workingHard.join();
        takeABreak.join();
    }

    public static void main(String[] args) throws InterruptedException {
        workingHardRoutine();
    }

}
