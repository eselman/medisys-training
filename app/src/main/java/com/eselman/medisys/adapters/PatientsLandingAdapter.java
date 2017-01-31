package com.eselman.medisys.adapters;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.Patient;

import java.util.List;

/**
 * Created by Evangelina Selman on 28/01/2017.
 */
public class PatientsLandingAdapter extends RecyclerView.Adapter<PatientsLandingAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Patient> patients;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holderId;
        TextView patientNameTextView;
        TextView patientIdNumberTextView;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                patientNameTextView = (TextView) itemView.findViewById(R.id.patientName);
                patientIdNumberTextView = (TextView) itemView.findViewById(R.id.patientIdNumber);
                holderId = 1;
            } else {
                holderId = 0;
            }
        }
    }

    public PatientsLandingAdapter(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public PatientsLandingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patiens_list_item, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patients_header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(PatientsLandingAdapter.ViewHolder holder, int position) {
        if (holder.holderId == 1) {
            Patient patient = patients.get(position - 1);
            String patientName = patient.getLastName() + ", " + patient.getFirstName();
            holder.patientNameTextView.setText(patientName);
            holder.patientIdNumberTextView.setText(patient.getIdentificationNumber());
        }
    }

    @Override
    public int getItemCount() {
        return patients.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
