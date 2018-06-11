package com.test.testprojrealm.data.network;

import android.util.Log;

import com.test.testprojrealm.BuildConfig;
import com.test.testprojrealm.data.network.pojo.BaseResponse;
import com.test.testprojrealm.data.network.pojo.ImageResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCommunicator {
    private static final int TIMEOUT = 10;
    private static final int RETRY_ATTEMPTS = 4;
    private static final String END_POINT = "https://api.gettyimages.com/v3/search/";
    private static final String KEY_API = "a3qbuh9kxab7qmaybtbsk3b7" ;///TODO MOVE THIS TO GRADLE
    private ApiServ mService;

    public ApiCommunicator() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        mService =  new Retrofit.Builder()
                .baseUrl(END_POINT)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiServ.class);

    }


    public Single<BaseResponse> getImages(String words) {
        return  mService.getImagesByWords(words,KEY_API)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }




}
