package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essam.microprocess.dressamdaher.Adapter.ExamList_Rec_Adapter;
import com.essam.microprocess.dressamdaher.Adapter.ViewHolder;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.AddExam_pojo;
import com.essam.microprocess.dressamdaher.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExamList extends Fragment {
    @BindView(R.id.Exam_List_rec)
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_exam_list, container, false);
        ButterKnife.bind(this,v);
        ConfigRecyceler();

         return v;
    }
    void ConfigRecyceler(){

        Query reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.EXAMS.getRef());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExamList_Rec_Adapter adapter = new ExamList_Rec_Adapter(AddExam_pojo.class,R.layout.examlist_rec_layout,
               ViewHolder.class,reference);
        recyclerView.setAdapter(adapter);

    }

}
