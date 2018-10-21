package com.essam.microprocess.dressamdaher.Contracts;

import com.essam.microprocess.dressamdaher.JsonModel.Result_Pojo;

import java.util.List;

/**
 * Created by microprocess on 2018-10-18.
 */

public interface MyResultContract {
    interface view {

        void ConfigRecycler(List<Result_Pojo> result);

        void Problem(String s);
    }
    interface presenter {

        void getMyResults(String uid);

        void Problem(String s);

        void ConfigRecycler(List<Result_Pojo> result);
    }
    interface model {

        void getMyResults(String uid);
    }
}
