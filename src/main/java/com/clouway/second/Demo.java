package com.clouway.second;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        Counter counter1 = new Counter(0, 29, null );
        Counter counter2 = new Counter(0, 17, counter1);
        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);
        thread1.start();
        thread2.start();
    }
}
