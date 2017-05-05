package com.nevil.meizi.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nevil.meizi.R;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class BottomDialog extends BottomSheetDialog implements View.OnClickListener {
    private TextView saveView, setWallpaperView, cancelView;
    private Context mContext;

    public BottomDialog(@NonNull Context context) {
        super(context);
        mContext = context;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_save:

                break;
            case R.id.dialog_wallpaper:

                break;
            case R.id.dialog_cancel:
                this.dismiss();
                break;
        }
    }
}
