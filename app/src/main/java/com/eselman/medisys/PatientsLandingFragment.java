package com.eselman.medisys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eselman.medisys.adapters.PatientsLandingAdapter;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PatientsLandingFragment extends Fragment {
    private RecyclerView patientsRecyclerView;
    private RecyclerView.Adapter patientsLandingAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Patient> patients;

    private final static String PATIENTS_ASSET_FILE = "patients.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients_landing, container, false);

        getPatientsList();

        patientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.patientsRecyclerView);
        patientsLandingAdapter = new PatientsLandingAdapter(patients);
        patientsRecyclerView.setAdapter(patientsLandingAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        patientsRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    private void getPatientsList() {
        try {
            InputStream usersAssetIS = getActivity().getApplicationContext().getAssets().open(PATIENTS_ASSET_FILE);
            int size = usersAssetIS.available();
            byte[] buffer = new byte[size];
            usersAssetIS.read(buffer);
            usersAssetIS.close();
            String bufferString = new String(buffer);

            JSONArray patientsJsonArray = new JSONArray(bufferString);

            patients = new ArrayList<>();
            for (int i=0; i< patientsJsonArray.length(); i++) {
                // get each patient from json array.
                JSONObject patientJsonObject = patientsJsonArray.getJSONObject(i);
                Patient patient = parsePatientJsonObject(patientJsonObject);
                patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Patient parsePatientJsonObject (JSONObject patientJsonObject) throws JSONException {
        Patient patient = new Patient();

        patient.setFirstName(patientJsonObject.getString("firstName"));
        patient.setLastName(patientJsonObject.getString("lastName"));
        patient.setPhoneNumber(patientJsonObject.getString("phoneNumber"));
        patient.setMobilePhone(patientJsonObject.getString("mobilePhone"));
        patient.setIdentificationNumber(patientJsonObject.getString("identificationNumber"));
        //patient.setBirthDate(patientJsonObject.get);

        return patient;
    }
}
