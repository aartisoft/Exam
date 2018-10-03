package com.essam.microprocess.dressamdaher.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.R;

/**
 * Created by microprocess on 2018-10-03.
 */

public class Question_Bank_Frag extends Fragment implements View.OnClickListener {

    private FloatingActionButton show_addQ_frag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_bank_layout,container,false);
        show_addQ_frag = v.findViewById(R.id.show_addQ_frag);
        show_addQ_frag.setOnClickListener(this);
        return  v;
    }

    @Override
    public void onClick(View view) {

        if (view == show_addQ_frag){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.whenClickFAB_showFrag();

            }

        }

    }
}
