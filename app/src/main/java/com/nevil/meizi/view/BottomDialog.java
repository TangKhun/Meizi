package com.nevil.meizi.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nevil.meizi.R;
import com.nevil.meizi.network.GlideImageManager;
import com.nevil.meizi.util.FileUtil;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class BottomDialog extends BottomSheetDialog implements View.OnClickListener {
    private TextView saveView, setWallpaperView, cancelView;
    private Context mContext;
    private String mUrl;

    public BottomDialog(@NonNull Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_image, null);
        saveView = (TextView) rootView.findViewById(R.id.dialog_save);
        setWallpaperView = (TextView) rootView.findViewById(R.id.dialog_wallpaper);
        cancelView = (TextView) rootView.findViewById(R.id.dialog_cancel);
        setContentView(rootView);
        registerListener();
    }

    private void registerListener() {
        saveView.setOnClickListener(this);
        setWallpaperView.setOnClickListener(this);
        cancelView.setOnClickListener(this);
    }

    public void setNewData(String url) {
        mUrl = url;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_save:
                //GlideImageManager.downLoadImage(mContext, mUrl);
                FileUtil.downLoadImage(mContext, mUrl, false);
                break;
            case R.id.dialog_wallpaper:
                GlideImageManager.setWallPaper(mContext, mUrl);
                //FileUtil.downLoadImage(mContext, mUrl, true);
                break;
            case R.id.dialog_cancel:
                this.dismiss();
                break;
        }
        dismiss();
    }
}
