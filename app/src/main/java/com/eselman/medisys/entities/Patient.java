package com.eselman.medisys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */
public class Patient implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String mobilePhone;
    private String identificationNumber;
    private Date birthDate;
    private Address address;
    private MedicalInsurance insurance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public MedicalInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(MedicalInsurance insurance) {
        this.insurance = insurance;
    }

    public String getCompleteAddress(){
        StringBuilder addressBuilder = new StringBuilder("");

        if (this.getAddress() != null) {
            if(this.getAddress().getStreet() != null && !this.getAddress().getStreet().isEmpty()){
                addressBuilder.append(this.getAddress().getStreet());
            }

            if(this.getAddress().getNumber() != null && !this.getAddress().getNumber().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getNumber());
            }

            if(this.getAddress().getFloor() != null && !this.getAddress().getFloor().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getFloor());
            }

            if(this.getAddress().getApartment() != null && !this.getAddress().getApartment().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getApartment());
            }
        }

        return addressBuilder.toString();
    }
}
