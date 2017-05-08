package com.nevil.meizi.activity;


import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.nevil.meizi.R;
import com.nevil.meizi.base.BaseActivity;
import com.nevil.meizi.util.T;
import com.nevil.meizi.view.BottomDialog;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;


/**
 * 加载显示大图
 */
public class PictureShowActivity extends BaseActivity {

    @BindView(R.id.picture_dialog_image)
    PhotoView mPictureDialogImage;
    @BindView(R.id.picture_dialog_progress)
    ProgressBar mPictureDialogProgress;
    String url;

    @Override
    protected int setLayout() {
        return R.layout.activity_picture;
    }

    protected void initView() {
        Intent intent = getIntent();
         url = intent.getStringExtra("path");
        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                mPictureDialogProgress.setVisibility(View.GONE);
                T.showShortToast(PictureShowActivity.this, "大图加载失败，请稍后重试");
                finish();

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                mPictureDialogProgress.setVisibility(View.GONE);
                return false;
            }
        }).into(mPictureDialogImage);
    }

    @OnClick(R.id.picture_dialog_image)
    public void onViewClicked() {
        finish();
    }

    @OnLongClick(R.id.picture_dialog_image)
    boolean onLongClick(View v) {
        new BottomDialog(this,url).show();
        return true;
    }
}
