package com.test.testprojrealm.presentation;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.testprojrealm.R;
import com.test.testprojrealm.data.dataBase.Entitys.Images;
import com.test.testprojrealm.data.network.pojo.ImageResponse;
import com.test.testprojrealm.presentation.adapter.ImageAdapterDiffUtill;
import com.test.testprojrealm.presentation.adapter.ImageRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    protected Unbinder mUnbinder;
    private MainActivityContract.Presenter mPresenter;
    @BindView(R.id.et_input_field)
    protected EditText etInputField;
    @BindView(R.id.rv_items)
    protected RecyclerView rvItems;
    private ProgressDialog mDialog;  ///TODO ProgressDialog is deprecated

    private ImageRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        mPresenter = new MainActivityPresenter(this);
        mDialog = ProgressDialog.show(this, "",
                getString(R.string.progress_dialog_message), true);
        mPresenter.init();
        initInputEditText();
    }

    private void initInputEditText(){
        etInputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                int result = actionId & EditorInfo.IME_MASK_ACTION;
                switch(result) {
                    case EditorInfo.IME_ACTION_DONE:
                        mPresenter.findImages(view.getText().toString());
                        mDialog = ProgressDialog.show(view.getContext(), "",
                                getString(R.string.progress_dialog_message), true);
                        break;
                    case EditorInfo.IME_ACTION_NEXT:
                        mPresenter.findImages(view.getText().toString());
                        mDialog = ProgressDialog.show(view.getContext(), "",
                                getString(R.string.progress_dialog_message), true);
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void setResult(List<Images> result) {
        runOnUiThread(()->{
            if (mDialog != null) mDialog.dismiss();
            if(mAdapter == null)
                mAdapter = new ImageRecyclerAdapter(result);
            else {
                ImageAdapterDiffUtill productDiffUtilCallback = new ImageAdapterDiffUtill(mAdapter.getData(), result);
                DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback);
                mAdapter.setData(result);
                productDiffResult.dispatchUpdatesTo(mAdapter);
            }
            rvItems.setLayoutManager(new LinearLayoutManager(this));

            rvItems.setAdapter(mAdapter);
        });
    }

    @Override
    public void setError() {
        runOnUiThread(()-> {
            if (mDialog != null ) mDialog.dismiss();
            Toast.makeText(this, "we did not find anything",
                Toast.LENGTH_LONG).show();});
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) mUnbinder.unbind();
        super.onDestroy();

    }

}
