package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.essam.microprocess.dressamdaher.Adapter.MyResult_Rec_Adapter;
import com.essam.microprocess.dressamdaher.Contracts.MyResultContract;
import com.essam.microprocess.dressamdaher.JsonModel.Result_Pojo;
import com.essam.microprocess.dressamdaher.MainPresnter.MyResultPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyResults extends Fragment implements MyResultContract.view {


    @BindView(R.id.MyResult_rec)
    RecyclerView recyclerView;
    @BindView(R.id.backgroundground)
    ImageView backgroundground;

    MyResultContract.presenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment .
         View v = inflater.inflate(R.layout.fragment_my_results, container, false);
        ButterKnife.bind(this , v);
        ControlPanel.Title.setText(R.string.MyResult);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        //initialize Presenter .
        presenter = new MyResultPresenter(this);

        presenter.getMyResults();

        return v;
    }

    @Override
    public void ConfigRecycler(List<Result_Pojo> result) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);

        MyResult_Rec_Adapter adapter = new MyResult_Rec_Adapter(result);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void Problem(String s) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        backgroundground.setVisibility(View.VISIBLE);


    }

}
