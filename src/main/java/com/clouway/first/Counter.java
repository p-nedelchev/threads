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
            try {
                Thread.sleep(500);
                counter++;
            } catch (InterruptedException e) {
                printValue();
                break;
            }
        }
    }

    private void printValue() {
        System.out.println(counter);
    }
}
