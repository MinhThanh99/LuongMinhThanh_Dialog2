package com.minhthanh.getallcontactsandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.minhthanh.getallcontactsandroid.databinding.ActivityNoteBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ActivityNoteBinding binding;

    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_note);

        binding.tvMenuTags.setOnClickListener(new View.OnClickListener() {
            String[] strings = {"Android", "Java","Kotlin", "Flutter","Swift"};
            boolean[] booleans = {true, false, true, false, false};
            final List<String> listTags = Arrays.asList(strings);
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(NoteActivity.this)
                        .setTitle("Chọn ngôn ngữ bạn yêu thích?")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvMenuTags.setText("");
                                String tags = "";
                                for (int i = 0; i < booleans.length; i++) {
                                    boolean checked = booleans[i];

                                    if (checked) {
                                        tags = tags + listTags.get(i) ;
                                        if (i != booleans.length - 1) {
                                            tags = tags + ", ";
                                        }
                                    }
                                }
                                binding.tvMenuTags.setText(binding.tvMenuTags.getText() + tags);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.tvMenuWeeks.setOnClickListener(new View.OnClickListener() {
            String[] strings = {"Mon", "Tue","Wed", "Thu","Fri","Sat","Sun"};
            boolean[] booleans = {true, false, true, false, false,false,false};
            final List<String> listTags = Arrays.asList(strings);
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(NoteActivity.this)
                        .setTitle("Chọn ngày?")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvMenuWeeks.setText("");
                                String tags = "";
                                for (int i = 0; i < booleans.length; i++) {
                                    boolean checked = booleans[i];

                                    if (checked) {
                                        tags = tags + listTags.get(i) ;
                                        if (i != booleans.length - 1) {
                                            tags = tags + ", ";
                                        }
                                    }
                                }
                                binding.tvMenuWeeks.setText(binding.tvMenuWeeks.getText() + tags);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        //Spinner

        List<String> list = new ArrayList<>();
        list.add("Work");
        list.add("Friend");
        list.add("Family");

        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);

        binding.spMonHoc.setAdapter(stringArrayAdapter);

        //time picker

        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(NoteActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                minute = minute;

                                String time = hour + ":" + minute;

                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");

                                    binding.tvTime.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,minute);

                timePickerDialog.show();



            }
        });

        //datedialog

        binding.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailog();

            }
        });
    }

    public void showDatePickerDailog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NoteActivity.this,
                NoteActivity.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = dayOfMonth + "/" + month + "/" + year;
        binding.tvDate.setText(date);
    }
}