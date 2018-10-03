package com.essam.microprocess.dressamdaher.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.essam.microprocess.dressamdaher.R;

import java.util.Objects;

/**
 * Created by microprocess on 2018-10-03.
 */
public class AddQ_frag extends Fragment implements View.OnClickListener {


    Button buttonA , buttonB , buttonC , buttonD;
    Drawable trueClick,falseClick;
    private String selectAnswer = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trueClick  = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_true);
        falseClick = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_false);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.add_qestion_frag_layout,container,false);
        buttonA = v.findViewById(R.id.A);
        buttonB = v.findViewById(R.id.B);
        buttonC = v.findViewById(R.id.C);
        buttonD = v.findViewById(R.id.D);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {

        if (view == buttonA){
            selectAnswer = " حط بقا الاجابه الي انت عاوزها ";
            buttonA.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonB){
            selectAnswer = " حط بقا الاجابه الي انت عاوزها ";
            buttonB.setBackground(trueClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonC){

            selectAnswer = " حط بقا الاجابه الي انت عاوزها ";
            buttonC.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonD.setBackground(falseClick);

        }
        if (view == buttonD){

            selectAnswer = " حط بقا الاجابه الي انت عاوزها ";
            buttonD.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);

        }

    }
}