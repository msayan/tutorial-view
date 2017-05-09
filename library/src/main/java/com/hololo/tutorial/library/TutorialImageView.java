package com.hololo.tutorial.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TutorialImageView extends android.support.v7.widget.AppCompatImageView {


    public TutorialImageView(Context context) {
        super(context);
    }

    public TutorialImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TutorialImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
