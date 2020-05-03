package com.example.exam.view.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NewSongRecycler extends RecyclerView {

    private boolean flag = false;
    private float downX;
    public NewSongRecycler(@NonNull Context context) {
        super(context);
    }

    public NewSongRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewSongRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action =ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                flag = true;
                downX = ev.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}
