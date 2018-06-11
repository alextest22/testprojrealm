package com.test.testprojrealm.data.dataBase.Entitys;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Images extends RealmObject {

    @PrimaryKey
    private String id;
    private String url;
    private String requestWords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestWords() {
        return requestWords;
    }

    public void setRequestWords(String requestWords) {
        this.requestWords = requestWords;
    }
}
