package com.java.test01.test;

import com.java.test01.thread.ThreadTest01;
import com.java.test01.thread.ThreadTest02;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadTest01 t1 = new ThreadTest01();
        ThreadTest02 t2 = new ThreadTest02();

        Thread thread1 = new Thread(t1,"线程1");
        Thread thread2 = new Thread(t2,"线程2");
        thread1.start();
        thread2.start();

         while(!thread2.isAlive()){
             thread1.interrupt();
         }
    }
}
