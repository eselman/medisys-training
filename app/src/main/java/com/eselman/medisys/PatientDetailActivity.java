package com.eselman.medisys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button patientHistoryBtn;
    private FloatingActionButton editPatientFloatingBtn;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);

        if (patient != null) {
            String patientName = patient.getLastName() + ", " + patient.getFirstName();
            getSupportActionBar().setTitle(patientName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            patientIdNumberInput = (TextView) findViewById(R.id.patientIdNumber);
            patientAgeInput = (TextView) findViewById(R.id.patientAge);
            patientPhoneNumberInput = (TextView) findViewById(R.id.patientPhone);
            patientMobilePhoneInput = (TextView) findViewById(R.id.patientMobilePhone);
            patientAddressInput = (TextView) findViewById(R.id.patientAddress);
            patientInsuranceInput = (TextView) findViewById(R.id.patientInsurance);
            patientInsuranceNumberInput = (TextView) findViewById(R.id.patientInsuranceNumber);
            patientBirthDateInput = (TextView) findViewById(R.id.patientBirthDate);

            patientHistoryBtn = (Button) findViewById(R.id.patientHistoryBtn);
            patientHistoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent patientHistoryIntent = new Intent(PatientDetailActivity.this, PatientHistoryActivity.class);
                    Bundle patientHistoryBundle = new Bundle();
                    patientHistoryBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                    patientHistoryIntent.putExtra(Constants.PATIENT_EXTRA, patientHistoryBundle);
                    startActivity(patientHistoryIntent);
                }
            });

            editPatientFloatingBtn = (FloatingActionButton) findViewById(R.id.editPatientFloatingBtn);
            editPatientFloatingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editPatientIntent = new Intent(PatientDetailActivity.this, EditPatientDetailsActivity.class);
                    Bundle patientBundle = new Bundle();
                    patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                    editPatientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                    startActivity(editPatientIntent);
                }
            });

            updatePatientDetails(patient);
        }
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

