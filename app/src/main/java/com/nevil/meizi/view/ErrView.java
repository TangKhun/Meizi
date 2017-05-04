package com.nevil.meizi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nevil.meizi.R;
import com.nevil.meizi.listener.OnButtonClick;

/**
 * Created by Tangkun on 2017/5/3.
 */

public class ErrView extends RelativeLayout implements View.OnClickListener {
    ImageView mImageView;
    TextView mTextView;
    OnButtonClick mButtonClick;

    public ErrView(Context context) {
        super(context);
    }

    public ErrView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_errview, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) this.findViewById(R.id.err_image);
        mTextView = (TextView) this.findViewById(R.id.err_text);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mButtonClick != null) {
            mButtonClick.onItemClick();
        }
    }
}
