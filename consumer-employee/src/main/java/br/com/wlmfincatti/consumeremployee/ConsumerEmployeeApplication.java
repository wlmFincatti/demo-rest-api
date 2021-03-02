package br.com.wlmfincatti.consumeremployee;

import br.com.wlmfincatti.consumeremployee.service.EmployeeConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ConsumerEmployeeApplication {

    @Autowired
    private EmployeeConsumer consumer;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerEmployeeApplication.class, args);
    }

    @PostConstruct
    public void waitingForMessages() {
        consumer.receiveMessage();
    }

}
