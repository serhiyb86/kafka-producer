package org.example;

import guru.learningjournal.kafka.examples.types.PosInvoice;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerFactory {

    private static KafkaProducer<Integer, String> producer;
    private static KafkaProducer<Integer, String> transactionalProducer;
    private static KafkaProducer<String, PosInvoice> avroProducer;
    private static final Properties props = new Properties();

    static {
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "My Kafka producer client");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092, localhost:9093, localhost:9094");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }


public static KafkaProducer<Integer, String> getProducerInstance() {
        if (producer == null) {
            initProducer();
        }
        return producer;
    }

    public static KafkaProducer<String, PosInvoice> getAvroProducerInstance() {

        if (avroProducer == null) {
            initAvroProducer();
        }
        return avroProducer;
    }

    private static void initAvroProducer() {
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        avroProducer = new KafkaProducer<>(props);
    }

    public static KafkaProducer<Integer, String> getTransactionalProducerInstance() {
        if (transactionalProducer == null) {
            initTransactionalProducerProducer();
        }
        return transactionalProducer;
    }


    private static void initTransactionalProducerProducer() {
        final Properties transactionalProp = new Properties();
        transactionalProp.putAll(props);
        transactionalProp.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "T1");
        transactionalProducer = new KafkaProducer<>(transactionalProp);
    }

    private static void initProducer() {
         producer = new KafkaProducer<>(props);
    }
}
