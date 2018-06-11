package com.test.testprojrealm.data.manager;

import com.test.testprojrealm.data.network.ApiCommunicator;
import com.test.testprojrealm.data.network.pojo.ImageResponse;

import io.reactivex.Single;

public class Manager {
    ApiCommunicator apiCommunicator;

    public Manager() {
        apiCommunicator = new ApiCommunicator();
    }


    public Single<ImageResponse> getImages(String words) {
        return  apiCommunicator.getImages(words)
                .flatMap(response -> Single.just(response.getImageResponses().get(0)));
    }
}
