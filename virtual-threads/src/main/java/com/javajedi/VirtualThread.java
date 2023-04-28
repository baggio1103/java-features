package com.javajedi;

import org.apache.log4j.Logger;

import java.time.Duration;

public class VirtualThread {

    private static final Logger log = Logger.getLogger(VirtualThread.class);

    private static void oomExample() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        oomExample();
        log.info("Threads Done");
        Thread.startVirtualThread(
                () -> log.info("hello world")
        );
        Thread.sleep(100);
   }

}
