package com.shobhit.movieapp;

import android.app.Application;
import android.content.Context;

import com.shobhit.movieapp.dagger.AppComponent;
import com.shobhit.movieapp.dagger.AppModule;
import com.shobhit.movieapp.dagger.DaggerAppComponent;
import com.shobhit.movieapp.dagger.NetModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by abcplusd on 04/12/17.
 */

public class MovieApp extends Application {
    public AppComponent appComponent;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        appComponent = initDagger(this);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    protected AppComponent initDagger(MovieApp application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .netModule(new NetModule(application))
                .build();
    }
    public static Context getInstance(){
    return mContext;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
