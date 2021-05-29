package ru.job4j.queue;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

    @Test
    public void test1() throws InterruptedException {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();


        Thread.sleep(1000);
        consumer.join();
        producer.join();

        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(8));
        assertThat(queue.poll(), is(9));
    }
}