package com.i4i.hcvb.route26;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView outView = getActivity().findViewById(R.id.create_start_date_disp);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view,int year, int month, int day) {
        populateSetDate(year, month + 1, day);
    }

    public void populateSetDate(int year, int month, int day){
        String out = month + "/" + day + "/" + year;
        outView.setText(out);
    }
}
