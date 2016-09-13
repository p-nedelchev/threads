package com.clouway.third;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        Object lock = new Object();
        Counter counter = new Counter(8, lock);
        Counter counter1 = new Counter(8, lock);
        Thread thread1 = new Thread(counter);
        Thread thread2 = new Thread(counter1);
        thread1.start();
        thread2.start();
    }

}
