package com.example.exam.view.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecycler extends RecyclerView {

    private boolean flag = false;
    private int downX;
    public HomeRecycler(@NonNull Context context) {
        super(context);
    }

    public HomeRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
         super.onInterceptTouchEvent(e);
         return e.getAction() == MotionEvent.ACTION_MOVE;
    }
}
