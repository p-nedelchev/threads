package task2;

public class InterruptingThreads extends Thread {
    private String threadName;
    private int countTo;
    private int sleepTime;
    private int counter = 0;
    private InterruptingThreads threadToBeInterrupted;
    private boolean flag = true;

    public InterruptingThreads(String threadName, int countTo, int sleepTime) {
        this.threadName = threadName;
        this.countTo = countTo;
        this.sleepTime = sleepTime;
    }

    public void interrupt(InterruptingThreads thread) {
        this.threadToBeInterrupted = thread;
    }

    @Override
    public void run() {
        while (counter < countTo || flag) {
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupt.");
                return;
            }
            counter++;
            System.out.println(threadName + ": " + counter);
        }
        stopEnemyThread();
    }

    private void stopEnemyThread() {
        threadToBeInterrupted.flag = false;
    }
}
