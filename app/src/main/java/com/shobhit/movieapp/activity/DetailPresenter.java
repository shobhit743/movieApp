package com.shobhit.movieapp.activity;

/**
 * Created by abcplusd on 05/12/17.
 */

public class DetailPresenter {
    public interface RealmTransactions{
        void getDataFromRealm(Integer id);
        void saveRealmData(Integer id);
        void closeRealm();

    }
    public interface Views {
        void setLikeBtn();
        void setUnlikeBtn();

    }
}
