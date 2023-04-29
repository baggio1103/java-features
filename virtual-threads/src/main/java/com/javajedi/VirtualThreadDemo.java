package com.javajedi;

import org.apache.log4j.Logger;

import java.time.Duration;

public class VirtualThreadDemo {

    private static final Logger log = Logger.getLogger(VirtualThreadDemo.class);

    static void log(String message) {
        log.info(String.format("%s | %s", Thread.currentThread(), message));
    }

    static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * Running 100_000 threads concurrently causes OutOfMemoryError
    * since JVM Thread is just a wrapper around an OS Thread.
    *  Each time when a thread is created. An OS must allocate lots of memory
    * in the stack to store the Thread Context, native and Java call stacks.
    * It results in ending up using all the memory.
    * */
    private static void oomExample() {
        for (int i = 0; i < 100_000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private static Thread virtualThread(String name, Runnable runnable) {
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }

    public static void main(String[] args) throws InterruptedException {
//        oomExample();
        log.info("Threads Done");
        var virtualThread = virtualThread("shit", () -> {
            log("shit");
        });
        virtualThread.join();
    }

}
