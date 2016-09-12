package com.clouway.first;

import java.util.Scanner;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Counter counter = new Counter();
        Thread countingThread = new Thread(counter);
        countingThread.start();
        if(scanner.hasNext()) {
           countingThread.interrupt();
        }
    }
}
