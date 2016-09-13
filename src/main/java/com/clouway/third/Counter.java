package com.clouway.third;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Counter implements Runnable{
    private int end;
    private int counter;
    private Object lock;



    public Counter(int end, Object lock) {
        this.end = end;
        this.counter = 0;
        this.lock = lock;
    }

    public void run() {
        while (counter < end) {
            synchronized (lock) {
                counter++;
                System.out.println(Thread.currentThread().getName() + " :" + counter);
                lock.notifyAll();
                try {
                    if (counter < end) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
