package ru.job4j;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;
    private int count = 0;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            notifyAll();
        }
    }


    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(300);

        Thread one = new Thread(() -> {
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " resumed");
        }, "One");

        Thread two = new Thread(() -> {
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " resumed");
        }, "Two");

        one.start();
        two.start();
        Thread.sleep(100);

        for (int i = 0; i < 300; i++) {
            barrier.count();
        }
    }
}
