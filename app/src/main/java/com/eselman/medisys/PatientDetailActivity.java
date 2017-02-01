package com.eselman.medisys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class PatientDetailActivity extends AppCompatActivity {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Toolbar toolbar;

    private TextView patientIdNumberInput;
    private TextView patientAgeInput;
    private TextView patientPhoneNumberInput;
    private TextView patientMobilePhoneInput;
    private TextView patientAddressInput;
    private TextView patientInsuranceInput;
    private TextView patientInsuranceNumberInput;
    private TextView patientBirthDateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Patient patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientIdNumberInput = (TextView)findViewById(R.id.patientIdNumber);
        patientAgeInput = (TextView)findViewById(R.id.patientAge);
        patientPhoneNumberInput = (TextView) findViewById(R.id.patientPhone);
        patientMobilePhoneInput = (TextView) findViewById(R.id.patientMobilePhone);
        patientAddressInput = (TextView) findViewById(R.id.patientAddress);
        patientInsuranceInput = (TextView) findViewById(R.id.patientInsurance);
        patientInsuranceNumberInput = (TextView) findViewById(R.id.patientInsuranceNumber);
        patientBirthDateInput = (TextView) findViewById(R.id.patientBirthDate);

        updatePatientDetails(patient);
    }

    private void updatePatientDetails(Patient patient) {
        patientIdNumberInput.setText(patient.getIdentificationNumber());
        patientAgeInput.setText(patient.getAge().toString());
        patientPhoneNumberInput.setText(patient.getPhoneNumber());
        patientMobilePhoneInput.setText(patient.getMobilePhone());
        patientAddressInput.setText(patient.getCompleteAddress());
        patientInsuranceInput.setText(patient.getInsurance().getDescription());
        patientInsuranceNumberInput.setText(patient.getInsurance().getAffiliateNumber());

        String birthDateStr = simpleDateFormat.format(patient.getBirthDate());
        patientBirthDateInput.setText(birthDateStr);
    }
}

