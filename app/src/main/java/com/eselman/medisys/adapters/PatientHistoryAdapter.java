package com.eselman.medisys.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.PatientHistory;

import java.util.List;

/**
 * Created by Evangelina Selman on 02/02/2017.
 */
public class PatientHistoryAdapter extends RecyclerView.Adapter<PatientHistoryAdapter.ViewHolder> {
    private List<PatientHistory> patientHistoryRecords;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientHistoryTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            patientHistoryTextView = (TextView) itemView.findViewById(R.id.patientHistoryRecord);
        }
    }

    public PatientHistoryAdapter(List<PatientHistory> patientHistoryRecords) {
        this.patientHistoryRecords = patientHistoryRecords;
    }

    @Override
    public PatientHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_history_item, parent, false);
        return new ViewHolder(view);
      }

    @Override
    public void onBindViewHolder(PatientHistoryAdapter.ViewHolder holder, int position) {
        PatientHistory patientHistoryRecord = patientHistoryRecords.get(position);
        holder.patientHistoryTextView.setText(patientHistoryRecord.getCompleteHistoryRecord());
    }

    @Override
    public int getItemCount() {
        return patientHistoryRecords.size();
    }
}