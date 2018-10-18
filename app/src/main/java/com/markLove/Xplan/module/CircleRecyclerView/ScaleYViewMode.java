package com.markLove.Xplan.module.CircleRecyclerView;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ScaleYViewMode implements ItemViewMode{

    private float mScaleRatio = 0.001f;

    public ScaleYViewMode(){}

    public ScaleYViewMode(float scaleRatio) {
        mScaleRatio = scaleRatio;
    }

    @Override
    public void applyToView(View v, RecyclerView parent) {
        float halfHeight = v.getHeight() * 0.5f;
        float parentHalfHeight = parent.getHeight() * 0.5f;
        float y = v.getY();
        float rot = parentHalfHeight - halfHeight - y;

        float scale = 1.0f - Math.abs(rot) * mScaleRatio;
        ViewCompat.setScaleX(v, scale);
        ViewCompat.setScaleY(v, scale);
    }
}
