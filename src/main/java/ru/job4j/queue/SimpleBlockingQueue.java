package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int SIZE;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();


    public SimpleBlockingQueue(int size) {
        SIZE = size;
    }

    public synchronized  void offer(T value) {
        while (queue.size() == SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() {
        T item;
        while ((item = queue.poll()) == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
        return item;
    }
}
