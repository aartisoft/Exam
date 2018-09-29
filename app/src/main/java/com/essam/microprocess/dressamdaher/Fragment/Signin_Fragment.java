package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this , v );



        return  v ;

    }



    // goto Register fragment
    @OnClick(R.id.txt_register)
    void Goto_Register_fragment (View view){

        //add fragment
        try {
            getActivity().getSupportFragmentManager().beginTransaction()
              .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                      R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.Main_fragment,new Register_Fragment())
                    .addToBackStack(null).commit();

        }catch (Exception e){
            Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }


    }



}
