package com.nevil.meizi.activity;


import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.nevil.meizi.R;
import com.nevil.meizi.base.BaseActivity;
import com.nevil.meizi.network.GlideImageManager;
import com.nevil.meizi.view.BottomDialog;
import com.wang.avi.AVLoadingIndicatorView;

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
    AVLoadingIndicatorView mPictureDialogProgress;
    String url;

    @Override
    protected int setLayout() {
        return R.layout.activity_picture;
    }

    protected void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("path");
        GlideImageManager.loadImageNeedRequest(this, url, mPictureDialogImage, mPictureDialogProgress);
    }

    @OnClick(R.id.picture_dialog_image)
    public void onViewClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }else {
            finish();
        }
    }

    @OnLongClick(R.id.picture_dialog_image)
    boolean onLongClick(View v) {
        new BottomDialog(this, url).show();
        return true;
    }
}
