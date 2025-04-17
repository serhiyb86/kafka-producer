package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TransactionalKafkaProducer {
    public static void main(String[] args) {
        KafkaProducer<Integer, String> tpi = ProducerFactory.getTransactionalProducerInstance();
        tpi.initTransactions();

        tpi.beginTransaction();
        ProducerRecord<Integer, String> record = new ProducerRecord<>("my-topic-1", "Hello World-t1");
        tpi.send(record);
        ProducerRecord<Integer, String> record1 = new ProducerRecord<>("my-topic-2", "Hello World-t1");
        tpi.send(record1);
        tpi.commitTransaction();


        tpi.beginTransaction();
        ProducerRecord<Integer, String> record2 = new ProducerRecord<>("my-topic-1", "Hello World-t2");
        tpi.send(record2);
        ProducerRecord<Integer, String> record3 = new ProducerRecord<>("my-topic-2", "Hello World-t2");
        tpi.send(record3);
        tpi.abortTransaction();
        tpi.close();

    }
}
