package com.example.universityactivitytracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.universityactivitytracker.Converters;
import com.example.universityactivitytracker.R;
import com.example.universityactivitytracker.Record;
import com.example.universityactivitytracker.RecordAsync;

import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Spinner spinner;
    private EditText addNotes;
    private Button clockIn, clockOut;
    private TextView clockInValue, clockOutValue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View r = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = r.findViewById(R.id.dashboard_tv_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        addNotes = r.findViewById(R.id.dashboard_et_addNotes);
        clockIn = r.findViewById(R.id.dashboard_btn_clkIn);
        clockOut = r.findViewById(R.id.dashboard_btn_clkOut);
        spinner = (Spinner) r.findViewById(R.id.dashboard_sp_moduleSelector);
        clockInValue = r.findViewById(R.id.dashboard_tv_clockIn);
        clockOutValue = r.findViewById(R.id.dashboard_tv_clockOut);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.moduleCodes_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        clockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                clockInValue.setText(currentTime.toString());
            }
        });

        clockOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Date currentClockOutTime = Calendar.getInstance().getTime();

                String notes = addNotes.getText().toString();
                String moduleCode = spinner.getSelectedItem().toString();
                String clockInTime = clockInValue.getText().toString();
                String clockOutTime = currentClockOutTime.toString();

                Record record = new Record();
                record.setNote(notes);
                record.setModule(moduleCode);
                record.setClockIn(Converters.toDate(clockInTime));
                record.setClockOut(Converters.toDate(clockOutTime));

                RecordAsync recordAsync = new RecordAsync(getActivity().getApplicationContext());
                recordAsync.insertRecord(record);

                Toast.makeText(getContext(), "New Record Added", Toast.LENGTH_SHORT).show();
            }
        });

        return r;
    }

}
