package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int value;
        int incValue;
        do {
            value = count.get();
            incValue = ++value;
        } while (!count.compareAndSet(value, incValue));
    }

    public int get() {
        return count.get();
    }
}
