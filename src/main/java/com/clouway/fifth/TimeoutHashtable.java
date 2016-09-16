package com.clouway.fifth;

import java.util.*;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class TimeoutHashtable<K, V> {
    private final Hashtable<K,V> table;
    private Map<K, Long> keyUsage;
    private long timeout;

    public TimeoutHashtable(long timeout) {
        table = new Hashtable<K, V>();
        keyUsage = new LinkedHashMap<K, Long>();
        this.timeout = timeout;
    }

    public void put(K key, V value) {
        synchronized (table) {
            table.put(key, value);
            keyUsage.put(key, System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " added " + "(" + key + ", " + value + ")");
            table.notifyAll();
        }
    }

    public V get(K key) {
        synchronized (table) {
            if(table.get(key) != null) {
                keyUsage.put(key, System.currentTimeMillis());
                System.out.println(" Key: " + key + " is used");
                return table.get(key);
            }
            return null;
        }
    }

    public V remove(K key) {
        synchronized (table) {
            if (table.containsKey(key)) {
                V value = table.get(key);
                table.remove(key);
                return value;
            }
            return null;
        }
    }

   private boolean isExpired(K key) {
        long timeNow = System.currentTimeMillis();
        long delta = timeNow - keyUsage.get(key);
        return delta > timeout;
    }

    public void removeExpired() {
           Iterator<K> iter = keyUsage.keySet().iterator();
           while(iter.hasNext()) {
                K key = iter.next();
                if (isExpired(key)) {
                    synchronized (table) {
                        System.out.println("Key: " + key + " expired. Removed");
                        remove(key);
                        iter.remove();
                        table.notifyAll();
                    }
                }
            }
        }

}
