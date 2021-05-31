package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int size;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public SimpleBlockingQueue() {
        size = 1;
    }

    public synchronized  void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T item;
        while ((item = queue.poll()) == null) {
            wait();
        }
        notifyAll();
        return item;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
