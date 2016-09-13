package com.clouway.second;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Counter implements Runnable{
    private int end;
    private int counter;
    private boolean flag;
    private Counter concurrent;

    public Counter(int end) {
        this.end = end;
        this.flag = true;
        this.counter = 0;
    }

    public void run() {
        while(flag && counter <= end) {
            try {
                System.out.println("Thread (" + end + "): " + counter);
                Thread.sleep(500);
                counter ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (concurrent != null) {
            concurrent.stop();
        }
    }

    public void stop() {
        this.flag = false;
    }

    public void setConcurrent(Counter concurrent) {
        this.concurrent = concurrent;
    }
}
