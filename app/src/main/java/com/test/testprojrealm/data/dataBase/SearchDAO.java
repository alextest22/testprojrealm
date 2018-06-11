package com.test.testprojrealm.data.dataBase;


import com.test.testprojrealm.data.dataBase.Entitys.Images;
import com.test.testprojrealm.data.network.pojo.ImageResponse;

import java.util.List;

import io.realm.Realm;

public class SearchDAO extends RealmHelper {

    public List<Images> getCopyAllImage() {
        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(realm.where(Images.class).findAll());
    }

    public void setImage (ImageResponse image, String wordReqest) {
        executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(convertToImages(image,wordReqest));
        });
    }

    private Images convertToImages(ImageResponse image, String wordReqest){
      Images images = new Images();
      images.setId(image.getId());
      images.setRequestWords(wordReqest);
      images.setUrl(image.getDisplaySizes().get(0).getUri());
      return images;
    }

}
