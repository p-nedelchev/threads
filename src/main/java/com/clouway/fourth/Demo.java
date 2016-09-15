package com.clouway.fourth;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        final DynamicList list = new DynamicList(20);
        Thread producer = new Thread(new Runnable() {
            public void run() {
               for (int i = 0;; i++) {
                   list.add(i);
               }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            public void run() {
                while(true){
                    list.remove();
                }
            }
        });
        producer.start();
        consumer.start();


    }
}
