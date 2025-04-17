package org.example;

import java.io.File;

public class MultiThreadKafkaProducer {
    public static void main(String[] args) throws InterruptedException {

        File[] files = new File("data").listFiles();
        Thread[] threads = new Thread[files.length];
        int count = 0;
        for (File file : files) {
            Thread thread = new Thread(new MultiThreadKafkaDispatcher("my-topic", file));
            thread.start();
            threads[count] = thread;
            count++;
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }
}
