package com.test.testprojrealm.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.test.testprojrealm.R;
import com.test.testprojrealm.data.dataBase.Entitys.Images;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private List<Images> mData;


    public ImageRecyclerAdapter(List<Images> data) {
       mData = data;
    }

    public void setData(List<Images> mData) {
        this.mData = mData;
    }

    public List<Images> getData() {
        return mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        protected Context mContext;
        @BindView(R.id.tv_tittle)
        protected TextView tvText;
        @BindView(R.id.iv_image)
        protected ImageView ivImage;
        private Images mItems;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        protected void bind(final Images item) {
            if (item != null && !TextUtils.isEmpty(item.getRequestWords())) tvText.setText(item.getRequestWords());
            else tvText.setText(mContext.getString(R.string.unnamed));

            if (item != null) {
                Glide.with(mContext)
                        .load(item.getUrl())
                        .transition(withCrossFade())
                        .into(ivImage);
            }
        }


    }


}
