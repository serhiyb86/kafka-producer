package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class MultiThreadKafkaDispatcher implements Runnable {

    private final KafkaProducer<Integer, String> kafkaProducer;
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadKafkaDispatcher.class);
    private final String topic;
private File file;

    public MultiThreadKafkaDispatcher(final String topic, final File file) {
        this.kafkaProducer = ProducerFactory.getProducerInstance();
        this.topic = topic;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            lines.forEach(line -> {
                logger.info("Sending record: {}", line);
                ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>(topic, line);
                kafkaProducer.send(producerRecord);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
