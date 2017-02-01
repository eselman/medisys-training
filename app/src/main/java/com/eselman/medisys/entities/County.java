package com.eselman.medisys.entities;

import java.io.Serializable;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */

public class County implements Serializable{
    private Long id;
    private String code;
    private String name;
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
