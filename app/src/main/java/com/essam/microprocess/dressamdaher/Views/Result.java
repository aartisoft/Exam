package com.essam.microprocess.dressamdaher.Views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.Contracts.ResultContract;
import com.essam.microprocess.dressamdaher.Dialog.AlertDialog;
import com.essam.microprocess.dressamdaher.MainPresnter.ResultPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;

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
    String Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intial();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Message        = bundle.getString("Message");
            TableName      = bundle.getString("SqlTableName");
            finalDegree    = bundle.getString("final_degree");
//            AlertDialog alertDialog = new AlertDialog(this,Message);
//            alertDialog.show();


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

    }

    @Override
    public void TotalDegrees(int total) {
        txFinalDegree.setText(finalDegree);
        txDegree.setText(String.valueOf(total));
    }
}
