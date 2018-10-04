package com.essam.microprocess.dressamdaher.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Adapter.QuestionBankAdapter;
import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.Contracts.QuestionsBankContract;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainPresnter.Question_BankPresenter;
import com.essam.microprocess.dressamdaher.R;

import java.util.List;

/**
 * Created by microprocess on 2018-10-03.
 */

public class Question_Bank_Frag extends Fragment implements View.OnClickListener
                                                            , QuestionsBankContract.view {

    private FloatingActionButton show_addQ_frag;
    private RecyclerView recyclerView;
    QuestionsBankContract.presenter presenter;
    AnimatedDialog dialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_bank_layout,container,false);
        show_addQ_frag = v.findViewById(R.id.show_addQ_frag);
        recyclerView   = v.findViewById(R.id.rec);
        presenter      = new Question_BankPresenter(this);
        show_addQ_frag.setOnClickListener(this);

        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();
        //call data from firebase .
        presenter.callQuestionData();

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

    @Override
    public void RecyclerConfig(List<Questions_Form> Result) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        QuestionBankAdapter adapter = new QuestionBankAdapter(Result);
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
}
