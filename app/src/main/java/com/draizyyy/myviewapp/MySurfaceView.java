package com.draizyyy.myviewapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private final DrawThread drawThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder = holder;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
    class DrawThread extends Thread {
        private volatile boolean running = true;
        private Canvas canvas;
        private boolean isFirst = true;

        @Override
        public void run() {
            while(running) {
                try {
                    sleep(100);
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLUE);
                    circle.draw(canvas);
                    if (isFirst) {
                        isFirst = !isFirst;
                        circle.setCoordinates(canvas.getWidth() / 2f, canvas.getHeight() / 2f);
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    circle.updateDistance();
                    circle.update();
                } catch (Exception e) {
                    Log.e("RRR", e.getMessage());
                }
            }
        }
    }

    public MyCircle circle = new MyCircle();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        circle.moveToPoint(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }

    class MyCircle {
        float x, y, radius;
        float moveToX, moveToY, step, stepX, stepY;
        float distanceX, distanceY, distance;
        float amountOfSteps, counterOfSteps;
        boolean signX, signY;
        Paint paint;

        public final void setCoordinates(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public MyCircle() {
            moveToX = x;
            moveToY = y;
            signX = true;
            signY = true;
            step = 20;
            radius = 50;
            paint = new Paint();
            paint.setColor(Color.RED);
        }

        void moveToPoint(float moveToX, float moveToY) {
            this.moveToX = moveToX;
            this.moveToY = moveToY;

            distanceX = moveToX - x;
            if (distanceX < 0) {
                signX = false;
            }

            distanceY = moveToY - y;
            if (distanceY < 0) {
                signY = false;
            }

            distance = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));


            amountOfSteps = distance / step;
            counterOfSteps = 0;

            stepX = distanceX / amountOfSteps;
            stepY = distanceY / amountOfSteps;
        }

        void draw(Canvas canvas) {
            canvas.drawCircle(x, y, radius, paint);
        }

        void updateDistance() {
            if (distanceX > 0 && distanceY > 0) {
                distanceX -= stepX;
                distanceY -= stepY;
            }
            else if (distanceX < 0 && distanceY < 0) {
                distanceX += stepX;
                distanceY += stepY;
            }
            else if (distanceX < 0 && distanceY > 0) {
                distanceX += stepX;
                distanceY -= stepY;
            }
            else if (distanceX > 0 && distanceY < 0) {
                distanceX -= stepX;
                distanceY += stepY;
            }
        }

        void update() {
            if (counterOfSteps >= amountOfSteps) {
                stepX = 0;
                stepY = 0;
            }
            x += stepX;
            y += stepY;

            counterOfSteps += 1;
        }
    }
}
