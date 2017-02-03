package com.eselman.medisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.eselman.medisys.adapters.PatientHistoryAdapter;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.entities.PatientHistory;
import com.eselman.medisys.helpers.Constants;
import com.eselman.medisys.helpers.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientHistoryActivity extends AppCompatActivity {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private RecyclerView patientHistoryRecyclerView;
    private RecyclerView.Adapter patientHistoryAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Patient patient;
    private List<PatientHistory> patientHistoryRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getPatientHistoryRecords();

        patientHistoryRecyclerView = (RecyclerView) findViewById(R.id.patientHistoryRecyclerView);
        patientHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        patientHistoryRecyclerView.setLayoutManager(layoutManager);
        patientHistoryAdapter = new PatientHistoryAdapter(patientHistoryRecords);
        patientHistoryRecyclerView.setAdapter(patientHistoryAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent patientIntent = new Intent(this, PatientDetailActivity.class);
                Bundle patientBundle = new Bundle();
                patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                patientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                startActivity(patientIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getPatientHistoryRecords(){
        try {
            InputStream usersAssetIS = getApplicationContext().getAssets().open(Constants.PATIENT_HISTORY_ASSET_FILE);
            int size = usersAssetIS.available();
            byte[] buffer = new byte[size];
            usersAssetIS.read(buffer);
            usersAssetIS.close();
            String bufferString = new String(buffer);

            JSONArray patientHistoryJsonArray = new JSONArray(bufferString);

            patientHistoryRecords = new ArrayList<>();
            for (int i=0; i< patientHistoryJsonArray.length(); i++) {
                // get each patient history from json array.
                JSONObject patientHistoryJsonObject = patientHistoryJsonArray.getJSONObject(i);
                JSONObject patientJsonObject = patientHistoryJsonObject.getJSONObject("patient");
                if (patient.getId() == patientJsonObject.getLong("id")) {
                    PatientHistory patientHistoryRecord = parsePatientHistoryJsonObject(patientHistoryJsonObject);
                    patientHistoryRecords.add(patientHistoryRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PatientHistory parsePatientHistoryJsonObject(JSONObject patientHistoryJsonObj) throws Exception {
        PatientHistory patientHistoryRecord = new PatientHistory();

        patientHistoryRecord.setPatientWeight(patientHistoryJsonObj.getDouble("patientWeight"));
        patientHistoryRecord.setSymptoms(patientHistoryJsonObj.getString("symptoms"));
        patientHistoryRecord.setDiagnosis(patientHistoryJsonObj.getString("diagnosis"));
        patientHistoryRecord.setObservations(patientHistoryJsonObj.getString("observations"));

        String visitDateStr = patientHistoryJsonObj.getString("visitDate");
        Date visitDate = simpleDateFormat.parse(visitDateStr);
        patientHistoryRecord.setVisitDate(visitDate);
        patientHistoryRecord.setPatient(patient);

        return patientHistoryRecord;
    }
}
