package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CenterZoomLayoutManager2 extends LinearLayoutManager {
    private final float mShrinkAmount = 0.15f;
    private final float mShrinkDistance = 0.9f;

    public CenterZoomLayoutManager2(Context context) {
        super(context);
    }

    public CenterZoomLayoutManager2(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterZoomLayoutManager2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        scaleChild();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int orientation = getOrientation();
        if (orientation == HORIZONTAL) {
            scaleChild();
            return super.scrollHorizontallyBy(dx, recycler, state);
        } else {
            return 0;
        }
    }

    private void scaleChild() {
       /* float midPoint = getWidth() / 2.f;
        float d1 = mShrinkDistance * midPoint;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMidPoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f;
            float d = Math.min(d1, Math.abs(midPoint - childMidPoint));
            float scale = 1.25f - mShrinkAmount * d / d1;
            child.setScaleY(scale);
            child.setScaleX(scale);*/
        float midpoint = getWidth() / 2.f;
        float d0 = 0.f;
        float d1 = mShrinkDistance * midpoint;
        float s0 = 1.9f;
        float s1 = 1.f - mShrinkAmount;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMidpoint;
            if (findFirstVisibleItemPosition() == 0 && i == 0) {
                childMidpoint = getDecoratedRight(child) - child.getWidth() / 2.f;
            } else {
                childMidpoint = getDecoratedLeft(child) + child.getWidth() / 2.f;
            }

            float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
            float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
            child.setScaleX(scale);
            child.setScaleY(scale);


        }
    }
}