package br.com.wlmfincatti.demojpawithkafka.model;

import lombok.Getter;

@Getter
public enum Role {

    DEV("developer"), MANAGER("Manager");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
