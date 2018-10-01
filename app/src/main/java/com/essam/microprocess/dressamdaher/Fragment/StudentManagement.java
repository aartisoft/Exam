package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essam.microprocess.dressamdaher.Contracts.StudentManagementContract;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.R;

import java.util.List;

import butterknife.ButterKnife;


public class StudentManagement extends Fragment implements StudentManagementContract.view {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.student_mangment, container, false);
        ButterKnife.bind(this,v);
        return v ;
    }

    @Override
    public void RecyclerConfig(List<FullRegisterForm> Result) {

    }
}
