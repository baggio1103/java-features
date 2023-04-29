package com.javajedi.virtualthreads;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.javajedi.ThreadUtil.log;
import static com.javajedi.ThreadUtil.sleep;

public class Bathroom {

    private final Lock lock = new ReentrantLock();

    synchronized void useTheToilet() {
        log("I'm going to use the toilet");
        sleep(Duration.ofSeconds(1L));
        log("I'm done with the toilet");
    }

    void useTheToiletWithLock() {
        if (lock.tryLock()) {
            try {
                log("I'm going to use the toilet");
                sleep(Duration.ofSeconds(1L));
                log("I'm done with the toilet");
            } finally {
                lock.unlock();
            }
        }
    }

}
