package com.javajedi.virtualthreads;

import java.time.Duration;

import static com.javajedi.ThreadUtil.*;

/**
 * CorrectCooperativeScheduling and ErrorCooperativeScheduling are same examples,
 * However, in this example - workingHardConsciously has a sleep function inside a loop
 * So, that a VT reaches a blocking operation and yields control to another VT, and scheduled
 * by scheduler to be executed later.
 * */
public class CorrectCooperativeScheduling {

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
        System.setProperty("jdk.virtualThreadScheduler.minRunnable", "1");
    }

    static Thread workingHardConsciously() {
        return virtualThread("Hard work", () -> {
            log("I am working hard");
            while (alwaysTrue()) {
                sleep(Duration.ofMillis(100));
            }
        });
    }

    static Thread takeABreak() {
        return virtualThread("TakeBreak", () -> {
            log("I'm going to take a break");
            sleep(Duration.ofSeconds(1L));
            log("I'm done with the break");
        });
    }

    static void workingHardRoutineConsciously() throws InterruptedException {
        var hardWork = workingHardConsciously();
        var takeABreak = takeABreak();
        hardWork.join();
        takeABreak.join();
    }

    public static void main(String[] args) throws InterruptedException {
        workingHardRoutineConsciously();
    }

}
