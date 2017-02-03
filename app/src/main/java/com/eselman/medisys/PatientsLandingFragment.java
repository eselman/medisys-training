package com.eselman.medisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eselman.medisys.adapters.PatientsLandingAdapter;
import com.eselman.medisys.entities.Address;
import com.eselman.medisys.entities.MedicalInsurance;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;
import com.eselman.medisys.helpers.DividerItemDecoration;
import com.eselman.medisys.helpers.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientsLandingFragment extends Fragment {
    private RecyclerView patientsRecyclerView;
    private RecyclerView.Adapter patientsLandingAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Patient> patients;
    private FloatingActionButton addPatientFloatingBtn;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients_landing, container, false);

        getPatientsList();

        patientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.patientsRecyclerView);
        patientsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        patientsLandingAdapter = new PatientsLandingAdapter(patients);
        patientsRecyclerView.setAdapter(patientsLandingAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        patientsRecyclerView.setLayoutManager(layoutManager);

        patientsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                patientsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Patient patient = patients.get(position);

                Intent patientDetailIntent = new Intent(getActivity(), PatientDetailActivity.class);
                Bundle patientBundle = new Bundle();
                patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                patientDetailIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                startActivity(patientDetailIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addPatientFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.addPatientFloatingBtn);
        addPatientFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent addPatientIntent = new Intent(getActivity(), AddPatientActivity.class);
                startActivity(addPatientIntent);
            }
        });

        return rootView;
    }

    private void getPatientsList() {
        try {
            InputStream usersAssetIS = getActivity().getApplicationContext().getAssets().open(Constants.PATIENTS_ASSET_FILE);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Patient parsePatientJsonObject (JSONObject patientJsonObject) throws Exception {
        Patient patient = new Patient();

        patient.setId(patientJsonObject.getLong("id"));
        patient.setFirstName(patientJsonObject.getString("firstName"));
        patient.setLastName(patientJsonObject.getString("lastName"));
        patient.setPhoneNumber(patientJsonObject.getString("phoneNumber"));
        patient.setMobilePhone(patientJsonObject.getString("mobilePhone"));
        patient.setIdentificationNumber(patientJsonObject.getString("identificationNumber"));
        patient.setAge(patientJsonObject.getInt("age"));
        String birthDateStr = patientJsonObject.getString("birthDate");
        Date birthDate = simpleDateFormat.parse(birthDateStr);
        patient.setBirthDate(birthDate);
        patient.setPhoneNumber(patientJsonObject.getString("phoneNumber"));
        patient.setMobilePhone(patientJsonObject.getString("mobilePhone"));

        // Parse Address.
        JSONObject patientAddressJsonObj = patientJsonObject.getJSONObject("address");
        Address patientAddress = new Address();
        patientAddress.setStreet(patientAddressJsonObj.getString("street"));
        patientAddress.setNumber(patientAddressJsonObj.getString("number"));
        patientAddress.setFloor(patientAddressJsonObj.getString("floor"));
        patientAddress.setApartment(patientAddressJsonObj.getString("apartment"));
        patient.setAddress(patientAddress);

        // Parse Insurance
        JSONObject patientInsuranceJsonObj = patientJsonObject.getJSONObject("insurance");
        MedicalInsurance patientInsurance = new MedicalInsurance();
        patientInsurance.setDescription(patientInsuranceJsonObj.getString("description"));
        patientInsurance.setAffiliateNumber(patientInsuranceJsonObj.getString("affiliateNumber"));
        patient.setInsurance(patientInsurance);

        return patient;
    }
}
