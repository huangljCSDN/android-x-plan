package com.markLove.Xplan.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.markLove.Xplan.ui.widget.BubbleRelativeLayout;


/**
 * Created by luoyunmin on 2017/9/19.
 */

public class CoupleGiftPopupWindow extends PopupWindow {

    private BubbleRelativeLayout bubbleView;
    private Context context;

    public CoupleGiftPopupWindow(Context context) {
        this.context = context;
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(false);
        setOutsideTouchable(false);
        setClippingEnabled(false);

        ColorDrawable dw = new ColorDrawable(0);
        setBackgroundDrawable(dw);
    }

    public void setBubbleViewShadowColor(String color) {
        bubbleView.setShadowColor(color);
    }

    public void setBubbleView(View view) {
        bubbleView = new BubbleRelativeLayout(context);
        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        bubbleView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(bubbleView);
    }

    public View getBubbleView() {
        return bubbleView;
    }

    public void setParam(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void show(View parent) {
        show(parent, Gravity.TOP, getMeasuredWidth() / 2);
    }

    public void show(View parent, int gravity) {
        show(parent, gravity, getMeasuredWidth() / 2);
    }

    public void setOffset(int gravity, float bubbleOffset) {
        BubbleRelativeLayout.BubbleLegOrientation orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
        switch (gravity) {
            case Gravity.BOTTOM:
                orientation = BubbleRelativeLayout.BubbleLegOrientation.TOP;
                break;
            case Gravity.TOP:
                orientation = BubbleRelativeLayout.BubbleLegOrientation.BOTTOM;
                break;
            case Gravity.RIGHT:
                orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
                break;
            case Gravity.LEFT:
                orientation = BubbleRelativeLayout.BubbleLegOrientation.RIGHT;
                break;
            default:
                break;
        }
        bubbleView.setBubbleParams(orientation, bubbleOffset);
    }


    /**
     * 显示弹窗
     *
     * @param parent
     * @param gravity
     * @param bubbleOffset 气泡尖角位置偏移量。默认位于中间
     */
    public void show(View parent, int gravity, float bubbleOffset) {
        BubbleRelativeLayout.BubbleLegOrientation orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
        if (!this.isShowing()) {
            switch (gravity) {
                case Gravity.BOTTOM:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.TOP;
                    break;
                case Gravity.TOP:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.BOTTOM;
                    break;
                case Gravity.RIGHT:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
                    break;
                case Gravity.LEFT:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.RIGHT;
                    break;
                default:
                    break;
            }
            bubbleView.setBubbleParams(orientation, bubbleOffset); // 设置气泡布局方向及尖角偏移

            int[] location = new int[2];
            parent.getLocationOnScreen(location);

            switch (gravity) {
                case Gravity.BOTTOM:
                    showAtLocation(parent, Gravity.NO_GRAVITY, 0, location[1] + getMeasureHeight());
                    break;
                case Gravity.TOP:
                    showAtLocation(parent, Gravity.NO_GRAVITY, 0, location[1] - getMeasureHeight());
                    break;
                case Gravity.RIGHT:
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth(), location[1] - (parent.getHeight() / 2));
                    break;
                case Gravity.LEFT:
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - getMeasuredWidth(), location[1] - (parent.getHeight() / 2));
                    break;
                default:
                    break;
            }
        } else {
            this.dismiss();
        }
    }

    /**
     * 测量高度
     *
     * @return
     */
    public int getMeasureHeight() {
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popHeight = getContentView().getMeasuredHeight();
        return popHeight;
    }

    /**
     * 测量宽度
     *
     * @return
     */
    public int getMeasuredWidth() {
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = getContentView().getMeasuredWidth();
        return popWidth;
    }
}
