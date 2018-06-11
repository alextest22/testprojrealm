package com.test.testprojrealm.data.network;

import com.test.testprojrealm.data.network.pojo.BaseResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServ {

    @GET("images/")
    Single<BaseResponse> getImagesByWords(@Query("phrase") String phrase,@Header("Api-Key") String key);
}
