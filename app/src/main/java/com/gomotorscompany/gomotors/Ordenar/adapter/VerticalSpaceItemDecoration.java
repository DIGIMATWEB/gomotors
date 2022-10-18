package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mHorizontalSpaceHeight;
    private ordenarViewImpl myView;
    public VerticalSpaceItemDecoration(ordenarViewImpl ordenarView,int mHorizontalSpaceHeight) {
        this.mHorizontalSpaceHeight = mHorizontalSpaceHeight;
        this.myView=ordenarView;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        if(parent.getAdapter().getItemCount()==1)
        {
            outRect.left = mHorizontalSpaceHeight;
            outRect.right = mHorizontalSpaceHeight;
            parent.setScaleX(1.05f);
            parent.setScaleY(1.05f);
        }else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = mHorizontalSpaceHeight;

            } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.right = mHorizontalSpaceHeight;
            }
        }
    }
}