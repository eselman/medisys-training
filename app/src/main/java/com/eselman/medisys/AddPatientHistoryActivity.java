package com.eselman.medisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

public class AddPatientHistoryActivity extends AppCompatActivity {
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_history);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent patientIntent = new Intent(this, PatientHistoryActivity.class);
                Bundle patientBundle = new Bundle();
                patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                patientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                startActivity(patientIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
