package com.test.testprojrealm.presentation;


import com.test.testprojrealm.data.dataBase.Entitys.Images;
import com.test.testprojrealm.data.network.pojo.ImageResponse;

import java.util.List;

public interface MainActivityContract {

    interface View  {

        public void setResult(List<Images> result);

        public void setError();

    }

    interface Presenter {

        public void init();

        public void findImages(String wordReqest);
    }

}
