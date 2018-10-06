package com.essam.microprocess.dressamdaher.Fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class addExam extends Fragment implements addExamContract.view
        ,addExamTouchHelper.RecyclerItemTouchHelperListener


{

    @BindView(R.id.chosen_Qestions_Rec)
    RecyclerView recyclerView ;

    @BindView(R.id.questionssize)
   public TextView Questions_size;

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
    Button Btn_addExam;

    String final_degree;
    String hour , minute , second ;
    addExamContract.presenter presenter ;
    addExam_Rec_Adapter adapter;
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


        Btn_addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        //config your recycler view  .
        presenter = new addExamPresenter(this);
        presenter.CallgetQestionsToRecycleView();

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


    @Override
    public void ConfigRecyclerview(List<Questions_Form> Questions) {


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new addExamTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new addExam_Rec_Adapter(Questions,this);
        recyclerView.setAdapter(adapter);

        // update txt Question Size .
        Update_Questions_size(Questions.size());

    }

    @Override
    public void Problem(String Result) {

        Toast.makeText(getActivity(), Result + "", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {



        // remove the item from recycler view
        adapter.removeItem(viewHolder.getAdapterPosition());


    }


    @Override
    public void Update_Questions_size(int i) {
        Questions_size.setText(i+"");
    }

}
