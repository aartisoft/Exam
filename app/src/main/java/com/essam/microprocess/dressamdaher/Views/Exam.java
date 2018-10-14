package com.essam.microprocess.dressamdaher.Views;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Contracts.ExamContract;
import com.essam.microprocess.dressamdaher.Dialog.AlertDialog;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.MainPresnter.ExamPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Exam extends AppCompatActivity implements View.OnClickListener , ExamContract.view{
    @BindView(R.id.Liner1)
    LinearLayout layout1 ;

    @BindView(R.id.contains)
    RelativeLayout relativeLayout;

    @BindView(R.id.reltwo)
    RelativeLayout relativeLayout2;

    @BindView(R.id.answerOne)
    Button buttonA;

    @BindView(R.id.answerTwo)
    Button buttonB;

    @BindView(R.id.answerThree)
    Button buttonC;

    @BindView(R.id.answerFour)
    Button buttonD;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.Question)
    TextView textView;

    Animation LTR,RTL,downtoup;
    Drawable trueClick,falseClick;

    private String selectAnswer = "",ID_Qestion="";

    ExamContract.presenter presenter;
    SQLiteDatabase db;
    String TableName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        intialViews();
        //Animation
        Animation();

        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
             TableName = bundle.getString("SqlTableName");



            SQlHelper helper = new SQlHelper(this);
            db = helper.getWritableDatabase();
            presenter.getQuestion(db,TableName);
        }


    }
    private void intialViews(){

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        trueClick                 = Objects.requireNonNull(this).getDrawable(R.drawable.radiobutton_check_true);
        falseClick                = Objects.requireNonNull(this).getDrawable(R.drawable.radiobutton_check_false);

        presenter = new ExamPresenter(this);
    }
    private void Animation(){
        //Animation
        LTR =  AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        RTL = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        layout1.setAnimation(RTL);
        relativeLayout.startAnimation(LTR);
        relativeLayout2.startAnimation(downtoup);

    }


    @Override
    public void onClick(View view) {

        if (view == buttonA){

            selectAnswer = buttonA.getText().toString();
            buttonA.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonB){

            selectAnswer = buttonB.getText().toString();
            buttonB.setBackground(trueClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonC){

            selectAnswer = buttonC.getText().toString();
            buttonC.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonD.setBackground(falseClick);

        }
        if (view == buttonD){

            selectAnswer = buttonD.getText().toString();
            buttonD.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);

        }
        Toast.makeText(this,selectAnswer+"",Toast.LENGTH_SHORT).show();
    }

        @OnClick(R.id.Skip)
        void BtnSkip(View view){
            scrollView.scrollTo(5, 10);
            Animation();
            selectAnswer = "" ;
            buttonD.setBackground(falseClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            Toast.makeText(this,selectAnswer+"",Toast.LENGTH_SHORT).show();
        }


        @OnClick(R.id.Next)
        void BtnNext(View view){

            if(!selectAnswer.isEmpty()) {
                presenter.insertAnswerInSql(db,TableName,ID_Qestion,selectAnswer);
            }
            else {
                Toast.makeText(this,"يرجي اختيار اجابة",Toast.LENGTH_SHORT).show();
            }


        }

    @Override
    public void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer) {
        textView.setText(question);
        buttonA.setText(answerOne);
        buttonB.setText(answerTwo);
        buttonC.setText(answerThree);
        buttonD.setText(answerFour);
        this.ID_Qestion = ID_Qestion ;

    }

    @Override
    public void Problem(String s) {
        AlertDialog alertDialog = new AlertDialog(this,s);
        alertDialog.show();
    }

    @Override
    public void AnswerInserted() {

        buttonD.setBackground(falseClick);
        buttonB.setBackground(falseClick);
        buttonA.setBackground(falseClick);
        buttonC.setBackground(falseClick);
        Toast.makeText(this,selectAnswer+"",Toast.LENGTH_SHORT).show();

        //Last Thing.
        selectAnswer = "" ;
        scrollView.scrollTo(5, 10);
        Animation();
        presenter.getQuestion(db,TableName);
    }
}
