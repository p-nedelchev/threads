package task1;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CountingThread thread = new CountingThread();
        thread.start();
        System.out.println("Enter 'stop' to break counting of thread:");

        while (true) {
            if (scanner.nextLine().equals("stop")) {
                thread.stopCounting();
                return;
            }
        }
    }
}