package com.javajedi;

import com.javajedi.virtualthreads.VirtualThread;
import org.apache.log4j.Logger;

import java.time.Duration;

public class ThreadUtil {

    private static final Logger log = Logger.getLogger(VirtualThread.class);

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String message) {
        log.info(String.format("%s | %s", Thread.currentThread(), message));
    }

    public static Thread virtualThread(String name, Runnable runnable) {
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }

    public static boolean alwaysTrue() {
        return true;
    }

}
