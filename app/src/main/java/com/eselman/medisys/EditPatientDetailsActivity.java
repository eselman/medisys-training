package com.eselman.medisys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditPatientDetailsActivity extends AppCompatActivity {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Patient patient;
    private EditText patientFirstNameInput;
    private EditText patientLastNameInput;
    private EditText patientIdNumberInput;
    private ImageButton patientBirthDatePicker;
    private TextView patientBirthDateInput;
    private EditText patientStreetInput;
    private EditText patientStreetNumberInput;
    private EditText patientFloorInput;
    private EditText patientApartmentInput;
    private EditText patientPhoneNumberInput;
    private EditText patientMobilePhoneInput;
    private EditText patientInsuranceInput;
    private EditText patientInsuranceNumberInput;
    private Button savePatientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_details);

        // Assinging the toolbar object to the view and setting the Action bar to the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        getSupportActionBar().setTitle(patientName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeViews();
        loadPatientInfo();
        patientBirthDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog =  new DatePickerDialog(EditPatientDetailsActivity.this,R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        Date patientBirthDate = c.getTime();

                        patientBirthDateInput.setText(simpleDateFormat.format(patientBirthDate));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        savePatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updatePatientInfo();
                    Intent patientIntent = new Intent(EditPatientDetailsActivity.this, PatientDetailActivity.class);
                    Bundle patientBundle = new Bundle();
                    patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                    patientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                    startActivity(patientIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

    private void initializeViews(){
        patientFirstNameInput = (EditText) findViewById(R.id.patientFirstNameEditable);
        patientLastNameInput = (EditText) findViewById(R.id.patientLastNameEditable);
        patientIdNumberInput = (EditText) findViewById(R.id.patientIdNumberEditable);
        patientBirthDateInput = (TextView) findViewById(R.id.patientBirthDateEditable);
        patientBirthDatePicker = (ImageButton) findViewById(R.id.patientBirthDatePicker);
        patientStreetInput =(EditText) findViewById(R.id.patientStreet);
        patientStreetNumberInput = (EditText) findViewById(R.id.patientStreetNumber);
        patientFloorInput = (EditText) findViewById(R.id.patientFloor);
        patientApartmentInput = (EditText) findViewById(R.id.patientApartment);
        patientPhoneNumberInput = (EditText) findViewById(R.id.patientPhoneEditable);
        patientMobilePhoneInput = (EditText) findViewById(R.id.patientMobilePhoneEditable);
        patientInsuranceInput = (EditText) findViewById(R.id.patientInsuranceEditable);
        patientInsuranceNumberInput = (EditText) findViewById(R.id.patientInsuranceNumberEditable);
        savePatientBtn = (Button) findViewById(R.id.savePatientBtn);
    }

    private void loadPatientInfo(){
        patientFirstNameInput.setText(patient.getFirstName());
        patientFirstNameInput.setSelection(patientFirstNameInput.getText().length());
        patientLastNameInput.setText(patient.getLastName());
        patientLastNameInput.setSelection(patientLastNameInput.getText().length());
        patientIdNumberInput.setText(patient.getIdentificationNumber());
        patientIdNumberInput.setSelection(patientIdNumberInput.getText().length());
        String birthDateStr = simpleDateFormat.format(patient.getBirthDate());
        patientBirthDateInput.setText(birthDateStr);
        patientStreetInput.setText(patient.getAddress().getStreet());
        patientStreetInput.setSelection(patientStreetInput.getText().length());
        patientStreetNumberInput.setText(patient.getAddress().getNumber());
        patientStreetNumberInput.setSelection(patientStreetNumberInput.getText().length());
        patientFloorInput.setText(patient.getAddress().getFloor());
        patientFloorInput.setSelection(patientFloorInput.getText().length());
        patientApartmentInput.setText(patient.getAddress().getApartment());
        patientApartmentInput.setSelection(patientApartmentInput.getText().length());
        patientPhoneNumberInput.setText(patient.getPhoneNumber());
        patientPhoneNumberInput.setSelection(patientPhoneNumberInput.getText().length());
        patientMobilePhoneInput.setText(patient.getMobilePhone());
        patientMobilePhoneInput.setSelection(patientMobilePhoneInput.getText().length());
        patientInsuranceInput.setText(patient.getInsurance().getDescription());
        patientInsuranceInput.setSelection(patientInsuranceInput.getText().length());
        patientInsuranceNumberInput.setText(patient.getInsurance().getAffiliateNumber());
        patientInsuranceNumberInput.setSelection(patientInsuranceNumberInput.getText().length());
    }

    private void updatePatientInfo() throws Exception{
        patient.setFirstName(patientFirstNameInput.getText().toString());
        patient.setLastName(patientLastNameInput.getText().toString());
        patient.setIdentificationNumber(patientIdNumberInput.getText().toString());
        patient.setBirthDate(simpleDateFormat.parse(patientBirthDateInput.getText().toString()));
        patient.setPhoneNumber(patientPhoneNumberInput.getText().toString());
        patient.setMobilePhone(patientMobilePhoneInput.getText().toString());

        patient.getAddress().setStreet(patientStreetInput.getText().toString());
        patient.getAddress().setNumber(patientStreetNumberInput.getText().toString());
        patient.getAddress().setFloor(patientFloorInput.getText().toString());
        patient.getAddress().setApartment(patientApartmentInput.getText().toString());

        patient.getInsurance().setDescription(patientInsuranceInput.getText().toString());
        patient.getInsurance().setAffiliateNumber(patientInsuranceNumberInput.getText().toString());
    }
}
