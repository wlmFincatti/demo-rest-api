package br.com.wlmfincatti.consumeremployee.configuration;

import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "kafka.consumer.employee")
@Setter
public class EmployeeKafkaConsumerConfig {

    private String bootstrapServer;
    private String applicationName;
    private String groupId;
    private String schemaRegistry;
    private String topic;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, applicationName);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
        properties.put("schema.registry.url", schemaRegistry);
        return properties;
    }

    public String getTopic() {
        return topic;
    }
}
