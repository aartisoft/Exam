package com.essam.microprocess.dressamdaher.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Adapter.QuestionBankAdapter;
import com.essam.microprocess.dressamdaher.Adapter.RecyclerItemTouchHelper;
import com.essam.microprocess.dressamdaher.Adapter.ViewHolder;
import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.Contracts.QuestionsBankContract;
import com.essam.microprocess.dressamdaher.Dialog.AlertDialog;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainPresnter.Question_BankPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.X;

/**
 * Created by microprocess on 2018-10-03.
 */

public class Question_Bank_Frag extends Fragment
                                implements View.OnClickListener
                                         , QuestionsBankContract.view
                                         ,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener
        {

    private FloatingActionButton show_addQ_frag;
    private RecyclerView recyclerView;
    SearchView searchaboutquestion;
    QuestionsBankContract.presenter presenter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    AnimatedDialog dialog;

            QuestionBankAdapter adapter;
            TextView view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference        = firebaseDatabase.getReference(DataBase_Refrences.BANKQUESTIONS.getRef());


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_bank_layout,container,false);
        ControlPanel.Title.setText(R.string.questions);
        show_addQ_frag = v.findViewById(R.id.show_addQ_frag);
        recyclerView   = v.findViewById(R.id.rec);
        presenter      = new Question_BankPresenter(this);
        setHasOptionsMenu(true);
        show_addQ_frag.setOnClickListener(this);
        searchaboutquestion = v.findViewById(R.id.searchaboutquestion);
        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();
        //call data from firebase .
        presenter.callQuestionData();

        searchaboutquestion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter!=null){

                    adapter.getFilter().filter(query);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter!=null){
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });


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
            public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

                super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.search, menu);
            }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Search:

                        searchaboutquestion.setIconified(false); //Expand the search view

                        if (searchaboutquestion.isShown()){
                            searchaboutquestion.setVisibility(View.GONE);
                        }else {
                            searchaboutquestion.setVisibility(View.VISIBLE);
                        }

                        // Do Fragment menu item stuff here
                        return true;

                    default:
                        break;
                }

                return false;
            }

                    @Override
            public void RecyclerConfig(final List<Questions_Form> Result) {


                    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
                    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter     = new QuestionBankAdapter(Result,getActivity(),this);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    dialog.Close_Dialog();





            }

            @Override
            public void problem(String problem) {
                //close
                dialog.Close_Dialog();
                Toast.makeText(getActivity(), problem + "", Toast.LENGTH_LONG).show();

            }



            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {


                 view    = viewHolder.itemView.findViewById(R.id.tx);

                presenter.addQuestionToAddTestRecycler(QuestionBankAdapter.qestions.get(position).getQuestionID());


             }

            @Override
            public void sentSuccessfully(String Result) {

                 view.setText("تم الإرسال");

            }

            @Override
            public void updateFragbyValuesTogoEditFrag(String questionID) {

                ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

                if (controlUI!=null){

                    controlUI.editQuestions(questionID,"Editing");

                }


            }

            @Override
            public void removingQuestion(final String questionID, final int position) {

                 final Question_BankPresenter presenter = new Question_BankPresenter(this);

                final AlertDialog alertDialog = new AlertDialog(getActivity(),"تحذير"," متأكد من حذف هذا السؤال ؟");
                alertDialog.show();
                alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //delete
                        presenter.tellModletoDeleteQuestion(reference,questionID,position);
                        alertDialog.dismiss();

                        dialog.ShowDialog();

                    }
                });




            }

            @Override
            public void Q_Removed_InUI(int position) {
                dialog.Close_Dialog();
                Toast.makeText(getActivity(), "لقد تم حذف السؤال بنجاح", Toast.LENGTH_SHORT).show();
                if (adapter!=null){

                    adapter.remove(position);

                }
            }

            @Override
            public void Q_notRemoved_InUI() {
                dialog.Close_Dialog();
                Toast.makeText(getActivity(), "فيه مشكله راجع تاني ", Toast.LENGTH_SHORT).show();
            }

        }
