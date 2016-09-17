package com.clouway.fifth;

import java.util.Random;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        final TimeoutHashtable<Integer, Integer> table = new TimeoutHashtable<Integer, Integer>(5000);
        try {
            table.put(1, 15);
            table.put(2, 18);
            table.put(3, 17);
            Thread.sleep(10000);
            table.put(4, 15);
            table.put(6, 18);
            table.put(7, 17);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
