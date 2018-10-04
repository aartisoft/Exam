package com.essam.microprocess.dressamdaher.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.essam.microprocess.dressamdaher.Contracts.AddQuestionContract;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainPresnter.AddQ_Presnter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Utils.ViewsEmpty;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by microprocess on 2018-10-03.
 */
public class AddQ_frag extends Fragment implements View.OnClickListener , AddQuestionContract.AddQUI {


    Button buttonA , buttonB , buttonC , buttonD , savingData;
    Drawable trueClick,falseClick;
    private String selectAnswer = "";
    EditText writeQuestion,answerOne,answerTwo , answerThree , answerFour;
    FirebaseDatabase firebaseDatabaseQuestions;
    DatabaseReference databaseReferenceQuestions;
    AnimatedDialog animatedDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trueClick                 = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_true);
        falseClick                = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_false);
        firebaseDatabaseQuestions = FirebaseDatabase.getInstance();
        databaseReferenceQuestions= firebaseDatabaseQuestions.getReference(DataBase_Refrences.BANKQUESTIONS.getRef());
        animatedDialog            = new AnimatedDialog(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v        = inflater.inflate(R.layout.add_qestion_frag_layout,container,false);
        buttonA       = v.findViewById(R.id.A);
        buttonB       = v.findViewById(R.id.B);
        buttonC       = v.findViewById(R.id.C);
        buttonD       = v.findViewById(R.id.D);
        savingData    = v.findViewById(R.id.savingData);
        writeQuestion = v.findViewById(R.id.writeQuestion);
        answerOne     = v.findViewById(R.id.answerOne);
        answerTwo     = v.findViewById(R.id.answerTwo);
        answerThree   = v.findViewById(R.id.answerThree);
        answerFour    = v.findViewById(R.id.answerFour);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);

        buttonD.setOnClickListener(this);
        savingData.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {

        if (view == buttonA){
            if (answerOne.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملي الحقل اولا حتي نتمك من اخذ الاجابه", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerOne.getText().toString();
            buttonA.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonB){
            if (answerTwo.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملي الحقل اولا حتي نتمك من اخذ الاجابه", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerTwo.getText().toString();
            buttonB.setBackground(trueClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonC){
            if (answerThree.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملي الحقل اولا حتي نتمك من اخذ الاجابه ", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerThree.getText().toString();
            buttonC.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonD.setBackground(falseClick);

        }
        if (view == buttonD){

            if (answerFour.getText().toString().isEmpty()){

                Toast.makeText(getActivity(), "قم بملي الحقل اولا حتي نتمك من اخذ الاجابه ", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerFour.getText().toString();
            buttonD.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);

        }
        if (view == savingData){


            ViewsEmpty.isEmpty(writeQuestion,getResources().getString(R.string.answerOne));
            ViewsEmpty.isEmpty(answerOne , getResources().getString(R.string.answerOne));
            ViewsEmpty.isEmpty(answerTwo , getResources().getString(R.string.answerTwo));
            ViewsEmpty.isEmpty(answerThree , getResources().getString(R.string.answerThre));
            ViewsEmpty.isEmpty(answerFour , getResources().getString(R.string.answerFour));
            if (selectAnswer.isEmpty()){

                Toast.makeText(getActivity(), "يرجي ادخال الاجابه الصحيحه", Toast.LENGTH_SHORT).show();

            }


            if (!writeQuestion.getText().toString().isEmpty()
                    && !answerOne.getText().toString().isEmpty()
                    && !answerTwo.getText().toString().isEmpty()
                    && !answerThree.getText().toString().isEmpty()
                    && !answerFour.getText().toString().isEmpty()
                    && !selectAnswer.isEmpty()){

                Questions_Form questions_form  = new Questions_Form(writeQuestion.getText().toString(),answerOne.getText().toString(),answerTwo.getText().toString(),answerThree.getText().toString()
                        ,answerFour.getText().toString(),databaseReferenceQuestions.push().getKey(),selectAnswer);
                animatedDialog.ShowDialog();
                AddQ_Presnter addQ_presnter    = new AddQ_Presnter(this);
                addQ_presnter.updateModelToSaveData(databaseReferenceQuestions,questions_form);

            }



        }

    }


    @Override
    public void dataSaved() {
        animatedDialog.Close_Dialog();
        selectAnswer = "";
        buttonD.setBackground(falseClick);
        buttonB.setBackground(falseClick);
        buttonA.setBackground(falseClick);
        buttonC.setBackground(falseClick);
        writeQuestion.setText("");
        answerOne.setText("");
        answerTwo.setText("");
        answerFour.setText("");
        answerThree.setText("");
        Toast.makeText(getActivity(), "لقد تم اضافه السؤال بنجاح", Toast.LENGTH_LONG).show();

    }

    @Override
    public void problem(String E) {
        animatedDialog.Close_Dialog();
        Toast.makeText(getActivity(), "لقد حدثت مشكله اثناء الاتصال ", Toast.LENGTH_LONG).show();

    }
}