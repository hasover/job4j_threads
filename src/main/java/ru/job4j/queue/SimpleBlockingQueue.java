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

    public SimpleBlockingQueue() {
        SIZE = 1;
    }

    public synchronized  void offer(T value) throws InterruptedException {
        while (queue.size() == SIZE) {
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
}
