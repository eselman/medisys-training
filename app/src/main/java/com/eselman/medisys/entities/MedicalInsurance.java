package com.eselman.medisys.entities;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */

public class MedicalInsurance {
    private Long id;
    private String symbol;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
