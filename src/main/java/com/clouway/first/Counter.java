package com.clouway.first;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Counter implements Runnable {
    private int counter;

    public Counter() {
        this.counter = 0;
    }

    public void run() {
        while(true) {
            counter++;
        }
    }

    public int getValue() {
        return this.counter;
    }
}
