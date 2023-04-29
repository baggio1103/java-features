package com.javajedi.virtualthreads;

import com.javajedi.ThreadUtil;

import static com.javajedi.virtualthreads.CorrectCooperativeScheduling.takeABreak;

/**
 * Set VM options the following arguments
 * -Djdk.virtualThreadScheduler.parallelism=1 -Djdk.virtualThreadScheduler.maxPoolSize=1 -Djdk.virtualThreadScheduler.minRunnable=1
 * <p>
 * They enable creating only one single thread
 * Pinned threads are threads that don't yield control to other threads even if they reach blocking operations.
 * Pinning happens when virtual threads execute some synchronized blocks or Java native calls. In this case,
 * Virtual threads do not pass control to other VTs. Platform Thread gets blocked as well.
 * To avoid pinning in synchronized blocks, it is possible using Locks as a way to deal with it.
 * <p>
 * Given    One Single Platform thread - two Virtual Threads
 * var bruce = goToTheToilet();
 * var daniel = takeABreak();
 * <p>
 * These actions happen at the same time and bruce yields control to daniel,
 * Since bruce executes in synchronized block and gets pinned, bruce does not pass control to daniel.
 * Therefore, the outcome will be sequential. See the logs.
 *
 * <p>
 * */
public class PinnedVirtualThread {

    private static final Bathroom bathroom = new Bathroom();

    static Thread goToTheToilet() {
        return ThreadUtil.virtualThread(
                "Go to the toilet",
                bathroom::useTheToilet
        );
    }

    static void twoEmployeesInTheOffice() throws InterruptedException {
        var bruce = goToTheToilet();
        var daniel = takeABreak();
        bruce.join();
        daniel.join();
    }

    public static void main(String[] args) throws InterruptedException {
        twoEmployeesInTheOffice();
    }

}