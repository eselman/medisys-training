package com.eselman.medisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.entities.PatientHistory;
import com.eselman.medisys.helpers.Constants;

import java.util.List;

public class PatientHistoryActivity extends AppCompatActivity {
    private Patient patient;

    private Toolbar toolbar;

    private RecyclerView patientHistoryRecyclerView;
    private RecyclerView.Adapter patientHistoryAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<PatientHistory> patientHistoryRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
