package com.essam.microprocess.dressamdaher.Fragment;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Adapter.StudentResult_Rec_Adapter;
import com.essam.microprocess.dressamdaher.Adapter.StudentsWrongs_Rec_Adapter;
import com.essam.microprocess.dressamdaher.JsonModel.WorngQestion;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentsWrongs extends Fragment {
    @BindView(R.id.txDegree)
    TextView txDegree;

    @BindView(R.id.txFinalDegree)
    TextView txFinalDegree;

    @BindView(R.id.circleimage)
    CircleImageView circleimage;

    @BindView(R.id.txName)
    TextView txName;

    @BindView(R.id.Wrongs_rec)
    RecyclerView Wrongs_rec;



    ArrayList<WorngQestion> WorngQestion ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ControlPanel.Title.setText(R.string.studentsWrongs);

        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_students_wrongs, container, false);
        ButterKnife.bind(this,v);
        WorngQestion = new ArrayList<>();
        if (getArguments() != null) {
            String name = getArguments().getString("Name");
            String total= getArguments().getString("Total");
            String FinalDegree= getArguments().getString("FinalDegree");
            int source = getArguments().getInt("Image");
            circleimage.setBackgroundResource(source);
            WorngQestion = getArguments().getParcelableArrayList("WrongQuestions");
            txDegree.setText(total);
            txFinalDegree.setText(FinalDegree);
            txName.setText(name);


        }

        StudentsWrongs_Rec_Adapter adapter = new StudentsWrongs_Rec_Adapter(WorngQestion);
        Wrongs_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        Wrongs_rec.setAdapter(adapter);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }



}
