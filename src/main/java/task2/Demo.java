package task2;

public class Demo {
    public static void main(String[] args) {
        InterruptingThreads thread1 = new InterruptingThreads("Thread-1", 10, 1000);
        InterruptingThreads thread2 = new InterruptingThreads("Thread-2", 15, 1500);
        thread1.interrupt(thread2);
        thread2.interrupt(thread1);
        thread1.start();
        thread2.start();
    }
}