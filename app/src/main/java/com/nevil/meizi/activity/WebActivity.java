package com.nevil.meizi.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.nevil.meizi.R;
import com.nevil.meizi.base.BaseActivity;
import com.nevil.meizi.util.FileUtil;

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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setSubtitle(url);
        mToolbar.setNavigationOnClickListener(v -> {
            WebActivity.this.finish();
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_browser) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
