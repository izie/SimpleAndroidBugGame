package com.MattLAB.BugGame.model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by changmatthew on 10/9/14.
 */

abstract class Bug {
    private CPoint xy;
    private CPoint xyDevice;
    private CPoint targetXY;
    private Paint paint;
    private boolean isHeated;

    public Bug() {
        this.paint = new Paint();
        targetXY = new CPoint(0,0);
        isHeated = false;
    }

    public CPoint getXyDevice() {
        return xyDevice;
    }

    public void setXyDevice(CPoint xyDevice) {
        this.xyDevice = xyDevice;
    }

    public void setPosition(CPoint xy){
        this.xy = xy;
        if(xy.getX() >= 0){
            targetXY.setX(xyDevice.getX());
        }
        if(xy.getY() >= 0){
            targetXY.setY(xyDevice.getY());
        }
    }

    public CPoint getXy() {
        return xy;
    }

    public void setXy(CPoint xy) {
        this.xy = xy;
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void draw(Canvas canvas) {

    }

    public CPoint getTargetXY() {
        return targetXY;
    }

    public void setTargetXY(CPoint targetXY) {
        this.targetXY = targetXY;
    }

    public boolean isHeated() {
        return isHeated;
    }

    public void setHeated(boolean isHeated) {
        this.isHeated = isHeated;
    }
}
