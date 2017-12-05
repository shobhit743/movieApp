package com.shobhit.movieapp.dagger;

import android.app.Application;

import com.shobhit.movieapp.BuildConfig;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.rest.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abcplusd on 04/12/17.
 */
@Module
public class NetModule {
    private Application application;

    public NetModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Named(Constants.BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }



    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(180, TimeUnit.SECONDS);
        httpClient.connectTimeout(180, TimeUnit.SECONDS);
        httpClient.writeTimeout(180, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        });
        return httpClient;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory, Converter.Factory converter, @Named(Constants.BASE_URL) String baseUrl, OkHttpClient.Builder okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient.build())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}

