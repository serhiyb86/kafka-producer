package org.example;

import guru.learningjournal.kafka.examples.types.PosInvoice;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.datagenerator.InvoiceGenerator;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableProducer implements Runnable {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(RunnableProducer.class);

   private final KafkaProducer<String, PosInvoice> producer;
   private final String topic;
   private final int timeout;
   AtomicBoolean stopper = new AtomicBoolean(false);
   private final int threadID;

    public RunnableProducer(String topic, int timeout, int threadID) {
        producer = ProducerFactory.getAvroProducerInstance();
        this.topic = topic;
        this.timeout = timeout;
        this.threadID = threadID;
    }

    @Override
    public void run() {
        try {
            logger.info("Starting producer thread - " + threadID);
            while (!stopper.get()) {
                Thread.sleep(timeout);
                InvoiceGenerator instance = InvoiceGenerator.getInstance();
                PosInvoice nextInvoice = instance.getNextInvoice();
                ProducerRecord<String, PosInvoice> record = new ProducerRecord<>(topic, nextInvoice.getStoreID().toString(), nextInvoice);
                producer.send(record);
                logger.info("Message sent to topic - " + topic + " threadID - " + threadID + " message:\n" + nextInvoice);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void shutdown() {
        logger.info("Shutting down producer thread - " + threadID);
        stopper.set(true);

    }
}
