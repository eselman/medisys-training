package com.eselman.medisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

public class EditPatientDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_details);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
