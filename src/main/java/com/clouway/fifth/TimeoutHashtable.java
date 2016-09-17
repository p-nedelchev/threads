package com.clouway.fifth;

import java.util.*;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class TimeoutHashtable<K, V> {
    private final Hashtable<K,V> table;
    private Map<K, Long> keyUsage;
    private long timeout;
    private Thread observer = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Iterator<K> iter = keyUsage.keySet().iterator();
                    while(iter.hasNext()) {
                        K key = iter.next();
                        long timeNow = System.currentTimeMillis();
                        long delta = timeNow - keyUsage.get(key);
                        if (delta > timeout) {
                            synchronized (table) {
                                System.out.println("Key: " + key + " expired. Removed");
                                remove(key);
                                iter.remove();
                            }
                        } else {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public TimeoutHashtable(long timeout) {
        table = new Hashtable<K, V>();
        keyUsage = new LinkedHashMap<K, Long>();
        this.timeout = timeout;
        observer.start();
    }

    public void put(K key, V value) {
        synchronized (table) {
            table.put(key, value);
            keyUsage.put(key, System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " added " + "(" + key + ", " + value + ")");
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

}
