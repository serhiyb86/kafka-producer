package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class HelloKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(HelloKafkaProducer.class);

    public static void main(String[] args) {
        KafkaProducer<Integer, String> producer = ProducerFactory.getProducerInstance();
        logger.info("Start sending data");
        for (int i = 0; i < 1000000; i++) {
            ProducerRecord<Integer, String> record = new ProducerRecord<>("my-topic", i, "Hello World" + i);
            producer.send(record);
        }
        logger.info("Finished sending 1000000 records");
producer.close();

    }
}
