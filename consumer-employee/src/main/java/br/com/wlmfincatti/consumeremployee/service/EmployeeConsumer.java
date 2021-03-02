package br.com.wlmfincatti.consumeremployee.service;

import br.com.wlmfincatti.consumeremployee.configuration.EmployeeKafkaConsumerConfig;
import br.com.wlmfincatti.consumeremployee.model.EmployeeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Slf4j
@Component
public class EmployeeConsumer {

    @Autowired
    private EmployeeKafkaConsumerConfig config;

    public void receiveMessage() {

        try (KafkaConsumer<String, EmployeeMessage> consumer = new KafkaConsumer<>(config.getProperties())) {
            consumer.subscribe(Collections.singletonList(config.getTopic()));
            while (true) {
                consumer.poll(Duration.of(1, ChronoUnit.DAYS))
                        .forEach(record -> {
                            log.info("partition: {} offset: {}, key: {}, value: {}", record.partition(), record.offset(), record.key(), record.value());
                        });
            }
        }
    }
}
