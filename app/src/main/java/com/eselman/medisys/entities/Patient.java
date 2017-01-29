package com.eselman.medisys.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mobilePhone;
    private String identificationNumber;
    private Date birthDate;
    private Address address;
    private Set<MedicalInsurance> insurances = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<MedicalInsurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<MedicalInsurance> insurances) {
        this.insurances = insurances;
    }
}
