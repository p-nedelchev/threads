package com.clouway.first;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Counter implements Runnable {
    private int counter;
    private boolean flag;


    public Counter() {
        this.counter = 0;
        this.flag = true;
    }

    public void run() {
        while(flag) {
            try {
                Thread.sleep(500);
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getValue() {
        return this.counter;
    }

    public void stop (){
        this.flag = false;
    }
}
