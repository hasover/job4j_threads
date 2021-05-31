package ru.job4j.queue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SimpleBlockingQueueTest {


    @Test
    public void test1() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(x -> {
                    try {
                        queue.offer(x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenFetchAllThenGetIt2() throws InterruptedException {
        final CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(
                () -> IntStream.range(3, 6).forEach(x -> {
                    try {
                        queue.offer("a".repeat(x));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList( "aaa", "aaaa", "aaaaa")));
    }
}