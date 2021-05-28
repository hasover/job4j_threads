package ru.job4j.queue;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

    @Test
    public void test1() {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                queue.offer(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                queue.poll();
            }
        });

        producer.start();
        consumer.start();

        try {
            Thread.sleep(1000);
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(8));
        assertThat(queue.poll(), is(9));
    }
}