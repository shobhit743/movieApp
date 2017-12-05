package com.shobhit.movieapp.dagger;

import android.app.Application;

import com.shobhit.movieapp.mainModule.MainActivity;
import com.shobhit.movieapp.rest.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by abcplusd on 04/12/17.
 */

@Singleton
@Component(modules = {AppModule.class,NetModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);



    Application getApplication();

    Converter.Factory getConverterFactory();

    RxJava2CallAdapterFactory getRxJava2CallAdapterFactory();

    Retrofit retrofit();

    ApiService apiInterface();

    OkHttpClient.Builder okHttpClient();
}

