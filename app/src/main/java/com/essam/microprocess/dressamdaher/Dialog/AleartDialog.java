package com.essam.microprocess.dressamdaher.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by microprocess on 2018-10-02.
 */

public class AleartDialog  extends Dialog {

    @BindView(R.id.title)
    TextView txTitle;
    @BindView(R.id.Message)
    TextView txMessage;
    @BindView(R.id.yes)
    public Button btnYes;
    @BindView(R.id.no)
    Button btnNo;

    Context context ;
    String Title;
    String Message;

    public AleartDialog(@NonNull Context context,String Title , String Message) {
        super(context);
        this.context = context;
        this.Title = Title;
        this.Message = Message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.aleartdialog);
        ButterKnife.bind(this);
        txTitle.setText(Title);
        txMessage.setText(Message);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



    }




}
