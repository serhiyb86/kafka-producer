package org.example;

public class AppConfig {
    public final String applicationID = "HelloProducer";
    public final String bootstrapServers = "localhost:9092, localhost:9093";
    public final String topicName = "HelloProducer";
    public final String numEvents = "1000000";
}
