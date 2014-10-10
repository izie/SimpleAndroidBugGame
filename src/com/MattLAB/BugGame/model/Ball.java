package com.MattLAB.BugGame.model;

import android.graphics.*;

/**
 * Created by changmatthew on 10/9/14.
 */
public class Ball {
    private CPoint xy;
    private Paint paint;
    private CPoint xyDevice;
    private final int radius = 3;
    double mAngle = -1;
    private boolean isHeated;


    public Ball(CPoint xy, CPoint xyDevice) {
        this.paint = new Paint();
        this.xy = xy;
        this.xyDevice = xyDevice;
        isHeated = false;
    }


    public void setPosition(CPoint xy){
        this.xy = xy;
    }


    public void draw(Canvas canvas) {
        if(!isHeated){
            paint.setColor(Color.CYAN);

            canvas.drawCircle(
                    getXy().getX()- radius,
                    getXy().getY()- radius,
                    radius * 2,
                    getPaint()
            );
        }


    }


    public CPoint getXy() {
        return xy;
    }

    public void setXy(CPoint xy) {
        this.xy = xy;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public CPoint getXyDevice() {
        return xyDevice;
    }

    public void setXyDevice(CPoint xyDevice) {
        this.xyDevice = xyDevice;
    }

    public float getRadius() {
        return radius;
    }

    public void update(CPoint xyMe) {
        if(mAngle == -1)
            mAngle = getAngle(xyMe);
        double diff = 12d;//총알 이동 속도.
        double radians = mAngle * (Math.PI / 180);
        xy.setX(xy.getX() + (float)(diff * Math.sin(radians)));
        xy.setY(xy.getY() + (float) (diff * Math.cos(radians)));


    }

    public double getmAngle() {
        return mAngle;
    }

    public void setmAngle(double mAngle) {
        this.mAngle = mAngle;
    }

    private double getAngle(CPoint xy1){
        float dx = xyDevice.getX() - xy1.getX();
        float dy = xyDevice.getY() - xy1.getY();

        double rad= Math.atan2(dx, dy);
        double degree = (rad*180)/Math.PI ;

        return degree;
    }

    public boolean isHeated() {
        return isHeated;
    }

    public void setHeated(boolean isHeated) {
        this.isHeated = isHeated;
    }
}
