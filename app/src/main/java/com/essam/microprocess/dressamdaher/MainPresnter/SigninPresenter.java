package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.SigninContract;
import com.essam.microprocess.dressamdaher.MainModle.SigninModel;

/**
 * Created by microprocess on 2018-09-30.
 */

public class SigninPresenter implements SigninContract.presenter {

    SigninContract.view view;
    SigninContract.model model;

    public SigninPresenter(SigninContract.view view){
        this.view = view;
        model = new SigninModel(this);
    }


    @Override
    public void passtocheck(String email, String password) {
      String result =  model.CheckisEmpty(email,password);
      view.checkResult(result);
    }

    @Override
    public void passlogIn(String email, String password) {
        model.logIn(email,password);
    }

    @Override
    public void updatelogInResult(String Result) {
        view.logInResult(Result);
    }




}
