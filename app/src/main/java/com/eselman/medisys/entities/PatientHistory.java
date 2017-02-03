package com.eselman.medisys.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eselman on 2/2/17.
 */
public class PatientHistory implements Serializable {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Long id;
    private Patient patient;
    private Date visitDate;
    private Double patientWeight;
    private String symptoms;
    private String diagnosis;
    private String observations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Double getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCompleteHistoryRecord(){
        StringBuilder patientHistoryBuilder = new StringBuilder("");

        if (this.getVisitDate() != null) {
            String visitDateStr = simpleDateFormat.format(this.getVisitDate());
            patientHistoryBuilder.append(visitDateStr);
        }

        if(this.getPatientWeight() != null){
            patientHistoryBuilder.append("|");
            patientHistoryBuilder.append(this.getPatientWeight().toString());
        }

        if(!this.getSymptoms().isEmpty()){
            patientHistoryBuilder.append("|");
            patientHistoryBuilder.append(this.getSymptoms());
        }

        if(!this.getDiagnosis().isEmpty()){
            patientHistoryBuilder.append("|");
            patientHistoryBuilder.append(this.getDiagnosis());
        }

        if(!this.getObservations().isEmpty()){
            patientHistoryBuilder.append("|");
            patientHistoryBuilder.append(this.getObservations());
        }

        return patientHistoryBuilder.toString();
    }
}
