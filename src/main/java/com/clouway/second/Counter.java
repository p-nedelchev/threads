package com.clouway.second;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Counter implements Runnable{
    private int beginning;
    private int ending;
    private int counter;
    private boolean flag;
    private Counter concurrent;

    public Counter(int beginning, int ending, Counter concurrent) {
        this.beginning = beginning;
        this.ending = ending;
        this.flag = true;
        this.counter = beginning;
        this.concurrent = concurrent;
    }

    public void run() {
        while(flag && counter <= ending) {
            try {
                System.out.println("Thread (" + beginning + ", " + ending + "): " + counter);
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

}
