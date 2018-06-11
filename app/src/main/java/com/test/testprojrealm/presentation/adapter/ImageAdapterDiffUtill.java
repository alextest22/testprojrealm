package com.test.testprojrealm.presentation.adapter;

import android.support.v7.util.DiffUtil;

import com.test.testprojrealm.data.dataBase.Entitys.Images;

import java.util.List;

public class ImageAdapterDiffUtill  extends DiffUtil.Callback {

        private final List<Images> oldList;
        private final List<Images> newList;

        public ImageAdapterDiffUtill(List<Images> oldList, List<Images> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Images oldProduct = oldList.get(oldItemPosition);
            Images newProduct = newList.get(newItemPosition);
            return oldProduct.getId().equals(newProduct.getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Images oldProduct = oldList.get(oldItemPosition);
            Images newProduct = newList.get(newItemPosition);
            return oldProduct.getUrl().equals(newProduct.getUrl())
                    && oldProduct.getRequestWords() == newProduct.getRequestWords();
        }

}
