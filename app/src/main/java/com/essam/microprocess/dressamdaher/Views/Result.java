package com.essam.microprocess.dressamdaher.Views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.Contracts.ResultContract;
import com.essam.microprocess.dressamdaher.Dialog.AlertDialog;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.JsonModel.WorngQestion;
import com.essam.microprocess.dressamdaher.MainPresnter.ResultPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Result extends AppCompatActivity implements ResultContract.view {

    @BindView(R.id.txDegree)
    TextView txDegree;

    @BindView(R.id.txFinalDegree)
    TextView txFinalDegree;

    @BindView(R.id.home)
    Button home ;

    SQlHelper helper;
    SQLiteDatabase db;
    String TableName = "", finalDegree = "";
    ResultContract.presenter presenter;
    AnimatedDialog dialog ;

    String total;
    private String Examname;
    private String ExamDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intial();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            TableName      = bundle.getString("SqlTableName");
            finalDegree    = bundle.getString("final_degree");
            Examname = bundle.getString("Examname");
            ExamDate = bundle.getString("ExamDate");



            presenter.CountDegree(db,TableName);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void intial(){
        ButterKnife.bind(this);
        presenter = new ResultPresenter(this);
         helper = new SQlHelper(this);
         db = helper.getWritableDatabase();
        dialog = new AnimatedDialog(this);
        dialog.ShowDialog();
    }

    @Override
    public void TotalDegrees(int total) {
        this.total = String.valueOf(total);
        txFinalDegree.setText(finalDegree);
        txDegree.setText(this.total);

        //get Wrong Questions .
        presenter.getWrongQestions(db,TableName);
    }

    @Override
    public void WrongQuestions(ArrayList<WorngQestion> worngQestions) {

        presenter.UploadResult(TableName.substring(1),FirebaseAuth.getInstance().getCurrentUser().getUid(),
                ExamDate,Examname,finalDegree, total ,worngQestions
                );

        dialog.Close_Dialog();
    }

    @Override
    public void UploadSuccessFull(String s) {
        dialog.Close_Dialog();
                    AlertDialog alertDialog = new AlertDialog(this,s);
                     alertDialog.show();
    }

    @Override
    public void ResultUploadFaild(String s) {
        dialog.Close_Dialog();
        AlertDialog alertDialog = new AlertDialog(this,s);
        alertDialog.show();
    }
}
