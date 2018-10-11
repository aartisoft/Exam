package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class AboutProgrammer extends Fragment {

    @BindView(R.id.circleImageView)
    CircleImageView Alaa ;

    @BindView(R.id.circleImageView2)
    CircleImageView Mohamed;

    @BindView(R.id.textView)
    TextView textView ;

    @BindView(R.id.textView2)
    TextView textView2;

    @BindView(R.id.Scrollview)
    ScrollView Scrollview;

    int MohamedX ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_about_programmer, container, false);
        ButterKnife.bind(this,view);

        textView.setAlpha(0);
        textView2.setAlpha(0);
        //Animation .
        Mohamed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                MohamedX = (int) Mohamed.getX();
                Alaa.animate().translationXBy(MohamedX).rotation(360).setDuration(1000);
                Mohamed.animate().translationXBy(-MohamedX).rotation(360).setDuration(1000);
                textView.animate().alpha(1).setDuration(3000);
                textView2.animate().alpha(1).setDuration(3000);
            }
        });

        Animation  downtoup = AnimationUtils.loadAnimation(getActivity(),R.anim.downtoup);
        Scrollview.setAnimation(downtoup);








        return view;
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
