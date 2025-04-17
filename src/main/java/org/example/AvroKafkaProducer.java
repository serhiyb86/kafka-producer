package org.example;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class AvroKafkaProducer {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(AvroKafkaProducer.class);



    public static void main(String[] args) {
        if (args.length != 3) throw new IllegalArgumentException("No arguments provided");

        String topic = args[0];
        int threads = Integer.parseInt(args[1]);
        int timeout = Integer.parseInt(args[2]);

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        List<RunnableProducer> runnables = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            RunnableProducer runnableProducer = new RunnableProducer(topic, timeout, i);
            runnables.add(runnableProducer);
            executor.submit(runnableProducer);
        }


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (RunnableProducer p : runnables) p.shutdown();

            executor.shutdown();
            logger.info("Closing Executor Service");
            try {
                executor.awaitTermination(timeout * 2, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));





    }
}
