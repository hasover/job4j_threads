package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {

    @Test
    public void whenTestPool() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger();
        ThreadPool pool = new ThreadPool();

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                integer.incrementAndGet();
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            pool.work(task);
        }

        Thread.sleep(5000);
        pool.shutdown();
        assertThat(integer.get(), is(1000));
    }

}