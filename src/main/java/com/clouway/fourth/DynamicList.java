package com.clouway.fourth;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class DynamicList {
    private final Integer [] list;
    private Integer size;
    private int index;

    public DynamicList(int length) {
        this.list = new Integer[length];
        this.size = length;
        this.index = 0;
    }

    public void add(Integer value){
        synchronized (list) {
            if(isFull()) {
                try {
                    System.out.println("List is full. " + Thread.currentThread().getName() + " is waiting...");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list[index] = value;
            System.out.println(Thread.currentThread().getName() + " added " + value);
            index++;
            list.notifyAll();
        }
    }

    public Integer remove() {
        synchronized (list) {
            if(isEmpty()) {
                try {
                    System.out.println("List is empty. " + Thread.currentThread().getName() + " is waiting");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index--;
            if(index < 0) {
                index = 0;
                return null;
            }
            list.notifyAll();
            System.out.println(Thread.currentThread().getName() + " removed " + list[index]);
            return list[index];
        }
    }

    public boolean isFull() {
        return index == size;
    }

    public boolean isEmpty() {
        return index < 0;
    }
}
