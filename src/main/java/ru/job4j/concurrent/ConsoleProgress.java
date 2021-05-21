package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable  {
    @Override
    public void run() {
        try {
            String[] process = {"-", "\\", "|", "/"};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + process[i++ % 4]);
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
