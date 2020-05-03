package com.example.exam.view.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HomeBottomRecycler extends RecyclerView {
    private float downX;
    public HomeBottomRecycler(@NonNull Context context) {
        super(context);
    }

    public HomeBottomRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeBottomRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
      int action = ev.getAction();
      switch (action){
          case MotionEvent.ACTION_DOWN:{
              getParent().requestDisallowInterceptTouchEvent(true);
          }
      }
      return super.dispatchTouchEvent(ev);
    }
}
