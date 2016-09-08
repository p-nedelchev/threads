import java.util.ConcurrentModificationException;

/**
 * Created by zumba on 11.08.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */

class Cleaner extends Thread {
    private CacheTable cache;
    private long sleepTime;

    Cleaner(long sleepTime, CacheTable cache) {
        this.sleepTime = sleepTime;
        this.cache = cache;

    }

    public void run() {

        while (cache.getContainer().size() > 0) {

            System.out.println("This is before clean" + cache.getContainer());

            clean();

            System.out.println("This is when cleaned" + cache.getContainer());

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignore) {

            }
        }
    }

    /**
     * Iterates through linked hash map by key set and check if the object exceeds its life span.
     */
    private void clean() {

        try {
            for (Object each : cache.getContainer().keySet()) {
                LifeSpan lifeSpan = (LifeSpan) cache.getContainer().get(each);
                if ((System.currentTimeMillis() - lifeSpan.startTime) >= lifeSpan.lifeSpan) {

                    cache.getContainer().remove(each);

                }
            }
        } catch (ConcurrentModificationException ignore) {

        }
    }
}
