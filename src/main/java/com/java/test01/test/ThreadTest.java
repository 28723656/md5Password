package com.java.test01.test;

import com.java.test01.thread.ThreadTest01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void main(String[] args) {

        ExecutorService execThread = Executors.newCachedThreadPool();

        int threadNum = 10;
        int stepNum = 10;

        for(int i=0;i<threadNum;i++){
            execThread.execute(new ThreadTest01(i*stepNum,i*stepNum+stepNum-1));
        }
        if(execThread.isShutdown()){
            System.out.println("isShutDown");
        }

        if(execThread.isTerminated()){
            System.out.println("isTerminated");
        }
        execThread.shutdown();

    }
}
