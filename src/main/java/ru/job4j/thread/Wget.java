package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable{
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }
    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream("downloaded.txt")) {

            byte[] dataBuffer = new byte[speed];
            int bytesRead = 0;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
                if (bytesRead == speed) {
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
