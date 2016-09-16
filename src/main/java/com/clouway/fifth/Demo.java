package com.clouway.fifth;

import java.util.Random;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        final TimeoutHashtable<Integer, Integer> table = new TimeoutHashtable<Integer, Integer>(5000);
        final Random generator = new Random();
        Thread addingThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    int randomKey = generator.nextInt(100);
                    int randomValue = generator.nextInt(100);

                    try {
                        table.put(randomKey, randomValue);
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        Thread checkingThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        table.removeExpired();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        addingThread.start();
        checkingThread.start();
    }
}
