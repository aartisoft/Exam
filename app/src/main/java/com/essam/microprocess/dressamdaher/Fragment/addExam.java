package com.essam.microprocess.dressamdaher.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Adapter.QuestionBankAdapter;
import com.essam.microprocess.dressamdaher.Adapter.RecyclerItemTouchHelper;
import com.essam.microprocess.dressamdaher.Adapter.addExamTouchHelper;
import com.essam.microprocess.dressamdaher.Adapter.addExam_Rec_Adapter;
import com.essam.microprocess.dressamdaher.Contracts.addExamContract;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainPresnter.addExamPresenter;
import com.essam.microprocess.dressamdaher.R;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addExam extends Fragment implements addExamContract.view  , addExamTouchHelper.RecyclerItemTouchHelperListener{

    @BindView(R.id.chosen_Qestions_Rec)
    RecyclerView recyclerView ;

    @BindView(R.id.questionssize)
    TextView Questions_size;


    static EditText et_second;


    static EditText et_minute;


    static EditText et_hour;

    @BindView(R.id.et_degree)
    EditText et_degree;

    @BindView(R.id.et_random_number_question)
    EditText et_random_number_question;

    @BindView(R.id.Final_Degree)
    TextView txFinal_Degree;

    @BindView(R.id.Btn_addExam)
    Button Btn_addExam;

    String final_degree;
    String hour , minute , second ;
    addExamContract.presenter presenter ;
    addExam_Rec_Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.add_exam, container, false);
         et_hour = v.findViewById(R.id.et_hour);
         et_minute = v.findViewById(R.id.et_minute);
        ButterKnife.bind(this,v);

        et_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dFragment = new TimePickerShow();
                // Show the date picker dialog fragment
                assert getFragmentManager() != null;
                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });

        et_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TimePickerShow();
                // Show the date picker dialog fragment
                assert getFragmentManager() != null;
                dFragment.show(getFragmentManager(), "Time Picker");
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


        Btn_addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!et_hour.getText().toString().isEmpty() && !et_minute.getText().toString().isEmpty()){


                    int hourinedit = Integer.parseInt(et_hour.getText().toString());
                    int miniteinedit = Integer.parseInt(et_minute.getText().toString());


                    if (hourinedit==0 && miniteinedit==0){

                        Toast.makeText(getActivity(), "ادخل ارقام صحيحه", Toast.LENGTH_SHORT).show();
                    }
                    else {


                        Toast.makeText(getActivity(), "كله تمااااااااااااااااااااام", Toast.LENGTH_SHORT).show();

                    }






                }






                }




        });

        //config your recycler view  .
        presenter = new addExamPresenter(this);
        presenter.CallgetQestionsToRecycleView();

        return v;
    }

//    void ShowTimePicker(){
//
//
//        Calendar mcurrentTime = Calendar.getInstance();
//        int hours = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//        int minute = mcurrentTime.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog;
//
//        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//
//                    et_hour.setText(String.valueOf(i));
//                    et_minute.setText(String.valueOf(i1));
//
//            }
//        },hours,minute,true);
//
//        timePickerDialog.setTitle("Select Time");
//        timePickerDialog.show();
//
//    }


    @Override
    public void ConfigRecyclerview(List<Questions_Form> Questions) {


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new addExamTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new addExam_Rec_Adapter(Questions,this);
        recyclerView.setAdapter(adapter);
        Update_Questions_size(Questions.size());

    }

    @Override
    public void Problem(String Result) {

        Toast.makeText(getActivity(), Result + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void Update_Questions_size(int lengh) {

        Questions_size.setText(lengh + "");

    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        adapter.removeItem(viewHolder.getAdapterPosition());

    }



    public static class TimePickerShow extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.YEAR);
            int minit = calendar.get(Calendar.MONTH);

            TimePickerDialog dpd = new TimePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,hour,minit,true);

            // Create a TextView programmatically.
            TextView tv = new TextView(getActivity());

            // Create a TextView programmatically
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                    RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setPadding(10, 10, 10, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            tv.setText("This is a custom title.");
            tv.setTextColor(Color.parseColor("#ff0000"));
            tv.setBackgroundColor(Color.parseColor("#FFD2DAA7"));

            dpd.setTitle("قم باختيار وقت الاختبار"); // Uncomment this line to activate it

            return  dpd;
        }



        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            et_hour.setText(i + "");
            et_minute.setText(i1 + "");

        }
    }




}
