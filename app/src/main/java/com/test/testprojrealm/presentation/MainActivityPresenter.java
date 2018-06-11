package com.test.testprojrealm.presentation;

import android.annotation.SuppressLint;

import com.test.testprojrealm.data.dataBase.SearchDAO;
import com.test.testprojrealm.data.manager.Manager;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mMainActivity;
    private Manager mManager;
    private SearchDAO mSearchDAO;

    public MainActivityPresenter(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mManager = new Manager();
        mSearchDAO = new SearchDAO();
    }


    @Override
    public void init() {
        mMainActivity.setResult(mSearchDAO.getCopyAllImage());
    }

    @SuppressLint("CheckResult")
    @Override
    public void findImages(String wordReqest) {
        mManager.getImages(wordReqest).subscribe(response-> {
            if(response == null)
                mMainActivity.setError();
            else {
                mSearchDAO.setImage(response, wordReqest);
                mMainActivity.setResult(mSearchDAO.getCopyAllImage());
            }
        },throwable -> {
            mMainActivity.setError();
        });
    }
}
