package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Adapter.StudentManagementAdapter;
import com.essam.microprocess.dressamdaher.Contracts.StudentManagementContract;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.MainPresnter.StudentMangementPresenter;
import com.essam.microprocess.dressamdaher.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudentManagement extends Fragment implements StudentManagementContract.view {

    @BindView(R.id.Management_Recycler)
    RecyclerView recyclerView;

    StudentManagementContract.presenter presenter ;
    AnimatedDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.student_mangment, container, false);
        ButterKnife.bind(this,v);
        presenter = new StudentMangementPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();

        //call data from firebase .
        presenter.callStudentData();

        //for menu (Search icon)
        setHasOptionsMenu(true);

        return v ;
    }

    @Override
    public void RecyclerConfig(List<FullRegisterForm> Result) {

        StudentManagementAdapter adapter = new StudentManagementAdapter(Result);
        recyclerView.setAdapter(adapter);
        //close
        dialog.Close_Dialog();

    }

    @Override
    public void problem(String problem) {
        //close
        dialog.Close_Dialog();
        Toast.makeText(getActivity(), problem + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Search:
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
    }
}