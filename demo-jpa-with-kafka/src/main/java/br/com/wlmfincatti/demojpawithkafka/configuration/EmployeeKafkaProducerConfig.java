package br.com.wlmfincatti.demojpawithkafka.configuration;

import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "kafka.producer.employee")
@Setter
public class EmployeeKafkaProducerConfig {

    private String topic;
    private String acks;
    private String bootstrapServer;
    private String schemaRegistry;
    private String applicationName;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        properties.put(ProducerConfig.ACKS_CONFIG, acks);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, applicationName);
        properties.put("schema.registry.url", schemaRegistry);
        return properties;
    }

    public String getTopic() {
        return topic;
    }
}
