package com.javajedi.virtualthreads;

import static com.javajedi.virtualthreads.ErrorCooperativeScheduling.takeABreak;
import static com.javajedi.virtualthreads.ErrorCooperativeScheduling.workingHard;

/**
 *  In this example, we are assigning two Platform Threads to two VT.*/
public class ConcurrentThreads {

    static void concurrentWorkingRoutine() throws InterruptedException {
        var hardWork = workingHard();
        var takeABreak = takeABreak();
        hardWork.join();
        takeABreak.join();
    }

    public static void main(String[] args) throws InterruptedException {
        concurrentWorkingRoutine();
    }

}
