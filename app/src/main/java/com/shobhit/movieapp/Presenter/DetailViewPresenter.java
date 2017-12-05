package com.shobhit.movieapp.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.shobhit.movieapp.R;
import com.shobhit.movieapp.activity.DetailActivity;
import com.shobhit.movieapp.activity.DetailPresenter;
import com.shobhit.movieapp.realm.IdName;
import com.shobhit.movieapp.realm.IdNameList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by abcplusd on 05/12/17.
 */

public class DetailViewPresenter implements DetailPresenter.RealmTransactions {

    private Realm realm;
    private final DetailPresenter.Views  mView;
    private Context mContext;
    private IdName idName;
    public DetailViewPresenter(DetailPresenter.Views view,Context context){
        this.mView = view;
        this.mContext = context;
    }


    @Override
    public void getDataFromRealm(Integer id) {
        getDataRealm(id);
    }

    @Override
    public void saveRealmData(Integer id) {
        saveDataInRealm(id);
    }

    @Override
    public void closeRealm() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    private void getDataRealm(Integer id) {
        realm = Realm.getDefaultInstance();
        idName = realm.where(IdName.class).equalTo(IdName.ID_KEY,id).findFirst();
        if (idName != null && idName.isLiked()) {
            mView.setLikeBtn();
        } else {
           mView.setUnlikeBtn();
        }
    }

    private void saveDataInRealm(final Integer id) {
        if (realm == null && realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }
        if (idName != null) {
            if (!idName.isLiked()) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        IdName idName = realm.where(IdName.class).equalTo(IdName.ID_KEY, id).findFirst();
                        idName.setLiked(true);


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        realm.beginTransaction();
                        idName.setLiked(true);
                        realm.commitTransaction();
                        mView.setLikeBtn();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("value", error.getLocalizedMessage());
                        Toast.makeText(mContext, mContext.getString(R.string.unable_to_save_data), Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        IdName idName = realm.where(IdName.class).equalTo(IdName.ID_KEY, id).findFirst();
                        idName.setLiked(false);


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        realm.beginTransaction();
                        idName.setLiked(false);
                        realm.commitTransaction();
                        mView.setUnlikeBtn();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("value", error.getLocalizedMessage());
                        Toast.makeText(mContext, mContext.getString(R.string.unable_to_save_data), Toast.LENGTH_LONG).show();
                    }
                });
            }
        } else {

            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    IdName idName1 = realm.createObject(IdName.class, id);
                    idName1.setLiked(true);
                    IdNameList idNameList = realm.where(IdNameList.class).findFirst();
                    if (idNameList == null) {
                        idNameList = realm.createObject(IdNameList.class);
                    }
                    if (idNameList.getIdNameRealmList() != null) {
                        idNameList.getIdNameRealmList().add(idName1);
                    } else {
                        RealmList<IdName> realmList = new RealmList<>();
                        realmList.add(idName1);
                        idNameList.setIdNameRealmList(realmList);
                    }

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    idName = realm.where(IdName.class).equalTo(IdName.ID_KEY,id).findFirst();
                    mView.setLikeBtn();

                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Log.d("value", error.getLocalizedMessage());

                }
            });

        }


    }

}
