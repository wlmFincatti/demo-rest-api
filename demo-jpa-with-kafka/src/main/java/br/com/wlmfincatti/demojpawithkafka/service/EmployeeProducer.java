package br.com.wlmfincatti.demojpawithkafka.service;

import br.com.wlmfincatti.demojpawithkafka.configuration.EmployeeKafkaProducerConfig;
import br.com.wlmfincatti.demojpawithkafka.model.EmployeeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EmployeeProducer {

    @Autowired
    private EmployeeKafkaProducerConfig config;

    public void sendMessage(EmployeeMessage message) {
        log.info("Message to sent: [{}]", message);
        ProducerRecord<String, EmployeeMessage> record = new ProducerRecord<>(config.getTopic(), message.getDocument().toString(), message);

        try (KafkaProducer<String, EmployeeMessage> producer = new KafkaProducer<>(config.getProperties())) {
            producer.send(record, (recordMetadata, e) -> {
                Optional.ofNullable(e)
                        .ifPresentOrElse(exception -> {
                            log.info("Erro to send message [{}] ", record.value());
                            log.error("Error: {}", e);
                        }, () -> {
                            log.info("Message [{}] send with sucess partition: {} offset: {}",
                                    record.value(),
                                    recordMetadata.partition(),
                                    recordMetadata.offset());
                        });
            });
        }
    }
}
