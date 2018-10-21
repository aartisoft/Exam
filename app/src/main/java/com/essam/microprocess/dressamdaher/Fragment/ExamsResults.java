package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.essam.microprocess.dressamdaher.Adapter.ExamsResults_Rec_Adapter;
import com.essam.microprocess.dressamdaher.Adapter.ViewHolder2;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.Result_Pojo;
import com.essam.microprocess.dressamdaher.JsonModel.Results_References;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExamsResults extends Fragment {

    @BindView(R.id.Exam_Result_rec)
    RecyclerView recyclerView ;
    @BindView(R.id.background)
    ImageView background;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ControlPanel.Title.setText(R.string.results);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment .
         View v =inflater.inflate(R.layout.fragment_exams_results, container, false);
        ButterKnife.bind(this,v);

        Query query = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef());

        ExamsResults_Rec_Adapter adapter = new ExamsResults_Rec_Adapter(Results_References.class,R.layout.examresult_rec_layout,
                ViewHolder2.class,query,getActivity().getSupportFragmentManager());


        //**// reverse Recycler view .
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        //**\\

        recyclerView.setAdapter(adapter);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                    background.setVisibility(View.GONE);
                }
                else {
                    ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                    background.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v ;
    }

}
