package com.draizyyy.myviewapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    private final Bitmap someBird;
    private final Paint paint = new Paint();
    private int currentColor = 0;

    public MyView(Context context) {
        super(context);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.comment);
        someBird = Bitmap.createScaledBitmap(bitmap, 70, 70, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (currentColor) {
            case 0:
                Log.v("RRR", "green");
                canvas.drawColor(Color.GREEN);
                break;
            case 1:
                Log.v("RRR", "ltgray");
                canvas.drawColor(Color.LTGRAY);
                break;
            default:
                Log.v("RRR", "yellow");
                canvas.drawColor(Color.YELLOW);
        }
        for (Pair<Float, Float> coordinate : coordinates) {
            canvas.drawBitmap(someBird, coordinate.first, coordinate.second, null);
        }
        drawStuff(canvas);

        Log.v("RRR", "onDraw");
        super.onDraw(canvas);
    }

    private final List<Pair<Float, Float>> coordinates = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentColor = (currentColor + 1) % 3;
        Log.v("RRR", "onTouch");
        coordinates.add(new Pair<>(event.getX(), event.getY()));
        invalidate();
        return super.onTouchEvent(event);
    }

    protected void drawStuff(Canvas canvas) {
        float rotateCenterX = 200;
        float rotateCenterY = 400;
        float rotateAngle = 45;
        canvas.rotate(-rotateAngle, rotateCenterX, rotateCenterY);
        canvas.drawText("OOOOOO", 1000, 1000, paint);
        canvas.rotate(rotateAngle, rotateCenterX, rotateCenterY);


        paint.setColor(Color.MAGENTA);
        paint.setTextSize(100);
        canvas.drawText("аоаооаоа", getWidth()/2f-400, getHeight()/2f, paint);



        canvas.drawRect(100, 100, 500, 1000, paint);
        paint.setColor(Color.GREEN);
        paint.setAlpha(205);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        canvas.drawCircle(200, 200, 180, paint);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);

        canvas.drawBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.comment), 300, 300, paint);
    }
}
