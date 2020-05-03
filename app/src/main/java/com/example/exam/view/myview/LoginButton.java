package com.example.exam.view.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.exam.R;

import java.util.jar.Attributes;

public class LoginButton extends androidx.appcompat.widget.AppCompatButton {
    private Paint paint= new Paint();
    private String text;
    private int color;
    private int textSize;
    public LoginButton(Context context) {
        super(context);
        init(context,null);
    }

    public LoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoginButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.LoginButton);
        text = array.getString(R.styleable.LoginButton_text);
        color = array.getColor(R.styleable.LoginButton_color,Color.RED);
        textSize = array.getInt(R.styleable.LoginButton_textSize,20);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        canvas.drawRoundRect(0,0,(float) getWidth(),(float) getHeight(),30,30,paint);
        paint.setColor(getResources().getColor(R.color.colorWhite));
        paint.setTextSize(textSize);
        canvas.drawText(text,(float) getWidth()/2 - 45,(float) getHeight()/2+20,paint);
    }
}
