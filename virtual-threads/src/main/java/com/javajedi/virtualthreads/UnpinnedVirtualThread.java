package com.javajedi.virtualthreads;

import com.javajedi.ThreadUtil;

import static com.javajedi.virtualthreads.CorrectCooperativeScheduling.takeABreak;

/**
 * Using Lock as a synchronization technique to avoid pinning threads.
 * To check what a pinning thread is, check out PinnedVirtualThread.class
 * */
public class UnpinnedVirtualThread {

    private static final Bathroom bathroom = new Bathroom();

    static Thread goToTheToilet() {
        return ThreadUtil.virtualThread(
                "Go to the toilet",
                bathroom::useTheToiletWithLock
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
