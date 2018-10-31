package com.markLove.Xplan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import com.markLove.Xplan.R;
import com.markLove.Xplan.base.mvp.BasePresenter;
import com.markLove.Xplan.base.ui.BaseActivity;
import com.markLove.Xplan.bean.GoNativeBean;
import com.markLove.Xplan.ui.widget.MyWebView;
import com.markLove.Xplan.utils.GsonUtils;
import com.markLove.Xplan.utils.LogUtils;
import com.markLove.Xplan.utils.StatusBarUtil;

/**
 *玩伴
 */
public class PlayersActivity extends BaseActivity {
    private MyWebView mWebView;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_players;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        fullScreen(this);
        StatusBarUtil.StatusBarLightModeAndFullscreen(this);
        mWebView = new MyWebView(this);
        LinearLayout mll = findViewById(R.id.rootView);
        mll.addView(mWebView);
        mWebView.addJavascriptInterface(new JSInterface(), "xplanfunc");
        mWebView.loadUrl("file:///android_asset/package/main/index.html#/find/playmate");
    }

    public class JSInterface {

        @JavascriptInterface
        public void goNative(String json) {
            //{"chatType":1,"chatId":1}
            LogUtils.i("huang", "goNative=" + json);
            GoNativeBean goNativeBean = GsonUtils.json2Bean(json,GoNativeBean.class);
            if (goNativeBean == null || goNativeBean.getCallFun().isEmpty()){
                finish();
            } else {
                Intent intent = new Intent();
                intent.putExtra("goNativeBean",goNativeBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        }

        @JavascriptInterface
        public void goNative() {
            finish();
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (mWebView.canGoBack()){
//            mWebView.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.removeAllViews();
        mWebView.stopLoading();
        mWebView.clearHistory();
        mWebView.clearCache(true);
        mWebView.destroy();
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }
}
