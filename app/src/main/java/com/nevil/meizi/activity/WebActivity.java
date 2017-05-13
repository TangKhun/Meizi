package com.nevil.meizi.activity;

import android.net.http.SslError;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.nevil.meizi.R;
import com.nevil.meizi.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    String url;

    @Override
    protected int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

        url = getIntent().getStringExtra("url");
        mToolbar.setTitle(getIntent().getStringExtra("TITLE"));
        Log.e("MEIZI", "initView: " + url);
        setSupportActionBar(mToolbar);
        mWeb.loadUrl(url);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);

            }
        });
        mWeb.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        mWeb.reload();
    }

    @Override
    protected void onDestroy() {
        mWeb.destroy();
        super.onDestroy();
    }
}
