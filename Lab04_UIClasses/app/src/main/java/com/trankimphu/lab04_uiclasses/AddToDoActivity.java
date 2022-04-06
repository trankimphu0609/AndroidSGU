package com.trankimphu.lab04_uiclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class AddToDoActivity extends AppCompatActivity {

    EditText edtTitle;
    RadioGroup rgStatus, rgPriority;
    TextView tvChooseDate, tvChooseTime;
    RadioButton rbStatus, rbPriority;
    Button btnChooseDate, btnChooseTime, btnCancel, btnReset, btnSubmit;

    private static String stringTime;
    private static String stringDate;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        edtTitle = (EditText) findViewById(R.id.edtTitle);

        rgStatus = (RadioGroup) findViewById(R.id.rgStatus);
        rgPriority = (RadioGroup) findViewById(R.id.rgPriority);

        tvChooseDate = (TextView) findViewById(R.id.tvChooseDate);
        tvChooseTime = (TextView) findViewById(R.id.tvChooseTime);

        rbStatus = (RadioButton) findViewById(R.id.rbNotDone);
        rbPriority = (RadioButton) findViewById(R.id.rbMedium);

        // Set default status
        setDefaultStatus();

        // Set default priority
        setDefaultPriority();

        // Set default date
//        setDefaultDate();

        // Set default time
//        setDefaultTime();

        // button choose date
        btnChooseDate = (Button) findViewById(R.id.btnChooseDate);
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });

        // button choose time
        btnChooseTime = (Button) findViewById(R.id.btnChooseTime);
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTime();
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Default Title
                setDefaultTitle();
//                edtTitle.setText("");

                // Default Status
                setDefaultStatus();

                // Default Priority
                setDefaultPriority();

//                rgStatus.clearCheck();
//                rgPriority.clearCheck();

                // Default Date and Time
//                tvChooseTime.setText("");
//                tvChooseDate.setText("");
                setDefaultDate();
                setDefaultTime();
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoItem.Priority priority = getPriority();
                // Get the current Priority

                ToDoItem.Status status = getStatus();
                // Get the current Status

                String stringTitle = getToDoTitle();
                // Get the current ToDoItem Title

                String date = stringDate + " " + stringTime;
                // Construct the Date string

                Intent intent = new Intent();
                ToDoItem.packageIntent(intent, stringTitle, priority, status, date);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void setDefaultDate() {

        Calendar calendar = Calendar.getInstance();

        setDateString(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        tvChooseDate.setText(stringDate);
    }

    public void setDateString(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        String month = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10) month = "0" + monthOfYear;
        if (dayOfMonth < 10) day = "0" + dayOfMonth;

        stringDate = year + "-" + month + "-" + day;
    }

    private void setDefaultTime() {
        date = new Date();
        date = new Date(date.getTime() + 604800000);
        // 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setTimeString(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.MILLISECOND));

        tvChooseTime.setText(stringTime);

    }

    public void setTimeString(int hourOfDay, int minute, int second) {
        String hour = "" + hourOfDay;
        String min = "" + minute;
        if (hourOfDay < 10)
            hour = "0" + hourOfDay;
        if (minute < 10)
            min = "0" + minute;
        stringTime = hour + ":" + min + ":00";
    }

    private String getToDoTitle() {
        return edtTitle.getText().toString();
    }

    private void setToDoTitle(String title) {
        edtTitle.setText(title);
    }

    private void setDefaultTitle() {
        setToDoTitle("");
    }

    private void chooseDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // set(year, month, day)
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tvChooseDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddToDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0 ,0 , 0, i , i1);
                tvChooseTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

//    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//
//        public Dialog onCreateDialog(Bundle bundle) {
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//
//        @Override
//        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            // set(year, monthOfYear, dayOfMonth)
//            setDateString(i, i1, i2);
//            tvChooseDate.setText(stringDate);
//        }
//    }
//
//    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
//
//        public Dialog onCreateDialog(Bundle bundle) {
//            Calendar calendar = Calendar.getInstance();
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//            int minute = calendar.get(Calendar.MINUTE);
//
//            return new TimePickerDialog(getActivity(), this, hour, minute, true);
//        }
//
//        @Override
//        public void onTimeSet(TimePicker timePicker, int i, int i1) {
//            setTimeString(i, i1, 0);
//            tvChooseTime.setText(stringTime);
//        }
//    }
//
    private ToDoItem.Status getStatus() {
        switch (rgStatus.getCheckedRadioButtonId()) {
            case R.id.rbNotDone: {
                return ToDoItem.Status.NOTDONE;
            }
            default: {
                return ToDoItem.Status.DONE;
            }
        }
    }

    private void setDefaultStatus() {
        rgStatus.check(rbStatus.getId());
    }

    private ToDoItem.Priority getPriority() {
        switch (rgPriority.getCheckedRadioButtonId()) {
            case R.id.rbLow: {
                return ToDoItem.Priority.LOW;
            }
            case R.id.rbMedium: {
                return ToDoItem.Priority.MED;
            }
            default: {
                return ToDoItem.Priority.HIGH;
            }
        }
    }

    private void setDefaultPriority() {
        rgPriority.check(rbPriority.getId());
    }

}