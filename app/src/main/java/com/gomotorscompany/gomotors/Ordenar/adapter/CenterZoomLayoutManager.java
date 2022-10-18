package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;

public class CenterZoomLayoutManager extends LinearLayoutManager {
    private final float mShrinkAmount = 0.15f;
    private final float mShrinkDistance = 0.9f;
    private ordenarViewImpl myView;
    public CenterZoomLayoutManager(ordenarViewImpl myView,Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.myView=myView;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int orientation = getOrientation();
        if (orientation == HORIZONTAL) {
            String direction = "";
            int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
            float midpoint = getWidth() / 2.f;
            float d0 = 0.f;
            float d1 = mShrinkDistance * midpoint;
            float s0 = 1.9f;
            float s1 = 1.f - mShrinkAmount;

            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                float childMidpoint; //=(getDecoratedRight(child) + getDecoratedLeft(child)) / 2.f;
                if (findFirstVisibleItemPosition() == 0 && i == 0) {
                    direction="R";
                    childMidpoint = getDecoratedRight(child) - child.getWidth() / 2.f;
                } else {
                    direction="L";
                    childMidpoint = getDecoratedLeft(child) + child.getWidth() / 2.f;
                }
                float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
                float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
                child.setScaleX(scale);
                child.setScaleY(scale);

            }
            Log.e("recyclerArmatupizza4"," "+ scrolled);

                myView.detectFirstItem(scrolled);

            return scrolled;
        } else return 0;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scrollHorizontallyBy(0, recycler, state);


    }
}
