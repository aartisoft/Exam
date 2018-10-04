package com.essam.microprocess.dressamdaher.Fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addExam extends Fragment {

    @BindView(R.id.chosen_Qestions_Rec)
    RecyclerView recyclerView ;

    @BindView(R.id.questionssize)
    TextView Questions_size;

    @BindView(R.id.et_second)
    EditText et_second;

    @BindView(R.id.et_minute)
    EditText et_minute;

    @BindView(R.id.et_hour)
    EditText et_hour;

    @BindView(R.id.et_degree)
    EditText et_degree;

    @BindView(R.id.et_random_number_question)
    EditText et_random_number_question;

    @BindView(R.id.Final_Degree)
    TextView txFinal_Degree;

    @BindView(R.id.Btn_addExam)
    Button button;

    String hour , minute , second ;
    String final_degree;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.add_exam, container, false);
        ButterKnife.bind(this,v);

        et_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowTimePicker();
            }
        });

        et_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTimePicker();
            }
        });

        et_degree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_random_number_question.getText().toString().isEmpty()
                        || charSequence.length() == 0
                        || et_degree.getText().toString().equals("0")) {

                    // Do not Make any Thing .
                    txFinal_Degree.setText("0");

                }
                else {

                    int x = Integer.parseInt(et_random_number_question.getText().toString());
                    int y = Integer.parseInt(String.valueOf(charSequence));
                    final_degree = String.valueOf(x * y);
                    txFinal_Degree.setText(final_degree);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_random_number_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_degree.getText().toString().isEmpty()
                        || charSequence.length() == 0
                        || et_random_number_question.getText().toString().equals("0")) {

                    // Do not Make any Thing .
                    txFinal_Degree.setText("0");

                }
                else {

                    int x = Integer.parseInt(et_degree.getText().toString());
                    int y = Integer.parseInt(String.valueOf(charSequence));
                    final_degree = String.valueOf(x * y);
                    txFinal_Degree.setText(final_degree);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }

    void ShowTimePicker(){

        Calendar mcurrentTime = Calendar.getInstance();
        int hours = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    et_hour.setText(String.valueOf(i));
                    et_minute.setText(String.valueOf(i1));
            }
        },hours,minute,true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

}
