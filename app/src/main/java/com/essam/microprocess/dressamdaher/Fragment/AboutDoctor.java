package com.essam.microprocess.dressamdaher.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutDoctor extends Fragment {

    //card view
    @BindView(R.id.Press_on_CardView)
    CardView cardView;
    @BindView(R.id.Press_on_CardView2)
    CardView cardView2;
    @BindView(R.id.Press_on_CardView3)
    CardView cardView3;
    @BindView(R.id.Press_on_CardView4)
    CardView cardView4;
    @BindView(R.id.Press_on_CardView5)
    CardView cardView5;
    @BindView(R.id.Press_on_CardView6)
    CardView cardView6;

    //imageview
    @BindView(R.id.image_dropdown)
    ImageView dropdown ;
    @BindView(R.id.image_dropdown2)
    ImageView dropdown2 ;
    @BindView(R.id.image_dropdown3)
    ImageView dropdown3 ;
    @BindView(R.id.image_dropdown4)
    ImageView dropdown4 ;
    @BindView(R.id.image_dropdown5)
    ImageView dropdown5 ;
    @BindView(R.id.image_dropdown6)
    ImageView dropdown6 ;

    //Details layout .
    @BindView(R.id.Details_layout)
    RelativeLayout Details_layout;
    @BindView(R.id.Details_layout2)
    RelativeLayout Details_layout2;
    @BindView(R.id.Details_layout3)
    RelativeLayout Details_layout3;
    @BindView(R.id.Details_layout4)
    RelativeLayout Details_layout4;
    @BindView(R.id.Details_layout5)
    RelativeLayout Details_layout5;
    @BindView(R.id.Details_layout6)
    RelativeLayout Details_layout6;


    @BindView(R.id.facebook)
    ImageView facebook;
    @BindView(R.id.twiiter)
    ImageView twiiter;

    @BindView(R.id.scroll)
    ScrollView scroll;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_about_doctor, container, false);
        ButterKnife.bind(this,v);

        //animation
        cardView.setScaleX(.9f);
        cardView.setScaleY(.9f);
        cardView.animate().scaleX(1f).scaleY(1f).setDuration(500);
        //animation
        cardView2.setScaleX(.9f);
        cardView2.setScaleY(.9f);
        cardView2.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation
        cardView3.setScaleX(.9f);
        cardView3.setScaleY(.9f);
        cardView3.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation
        cardView4.setScaleX(.9f);
        cardView4.setScaleY(.9f);
        cardView4.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation
        cardView5.setScaleX(.9f);
        cardView5.setScaleY(.9f);
        cardView5.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation
        cardView6.setScaleX(.9f);
        cardView6.setScaleY(.9f);
        cardView6.animate().scaleX(1f).scaleY(1f).setDuration(500);



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout.isShown()){
                    Details_layout.setVisibility(View.GONE);
                    dropdown.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout.setVisibility(View.VISIBLE);
                    dropdown.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout);
                }
                focusOnView(cardView);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout2.isShown()){
                    Details_layout2.setVisibility(View.GONE);
                    dropdown2.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout2.setVisibility(View.VISIBLE);
                    dropdown2.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout2);
                }
                focusOnView(cardView2);

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout3.isShown()){
                    Details_layout3.setVisibility(View.GONE);
                    dropdown3.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout3.setVisibility(View.VISIBLE);
                    dropdown3.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout3);
                }
                focusOnView(cardView3);

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout4.isShown()){
                    Details_layout4.setVisibility(View.GONE);
                    dropdown4.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout4.setVisibility(View.VISIBLE);
                    dropdown4.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout4);
                }
                focusOnView(cardView4);

            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout5.isShown()){
                    Details_layout5.setVisibility(View.GONE);
                    dropdown5.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout5.setVisibility(View.VISIBLE);
                    dropdown5.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout5);
                }
                focusOnView(cardView5);

            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout6.isShown()){
                    Details_layout6.setVisibility(View.GONE);
                    dropdown6.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout6.setVisibility(View.VISIBLE);
                    dropdown6.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout6);
                }

                focusOnView(cardView6);

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity(),"https://www.facebook.com/Dr.Essamdaher");
                        facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        twiiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + "EssamDaher")));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + "EssamDaher")));
                }
            }
        });
        return v ;
    }

    void openAnimation(RelativeLayout CardDownlayout ){

        CardDownlayout.setScaleX(0.0f);
        CardDownlayout.setScaleY(0.0f);
        CardDownlayout.animate().scaleX(1f).scaleY(1f).setDuration(300);
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
    public String getFacebookPageURL(Context context , String FACEBOOK_URL ) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_URL;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
    private final void focusOnView(final CardView cardView){
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.smoothScrollTo(0, cardView.getTop());
            }
        });
    }
}
