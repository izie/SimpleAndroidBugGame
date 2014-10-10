package com.MattLAB.BugGame.model;

import android.graphics.*;

/**
 * Created by changmatthew on 10/9/14.
 */
public class Me {
    private CPoint xy;
    private Paint paint;
    private Paint paint2;
    private CPoint xyDevice;
    private float radius;
    private float arc;
    private int level = 1;
    private String msg;
    private int win_or_lose;

    public int getWin_or_lose() {
        return win_or_lose;
    }

    public void setWin_or_lose(int win_or_lose) {
        this.win_or_lose = win_or_lose;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int score = 0;

    public Me() {
        this.paint = new Paint();
        paint2 = new Paint();
        xy = new CPoint(0,0);
        arc = 0;
        msg = "";
    }


    public void setPosition(CPoint xy){
        this.xy = xy;
    }

    public void drawBackground(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(
                getXyDevice().getX(),
                getXyDevice().getY(),
                radius * 2,
                paint
        );
    }

    public void drawDashboard(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(18);
        canvas.drawText("Score : "+score,30,50,paint);
        canvas.drawText("Level : "+level,30,80,paint);
        canvas.drawText(msg,300,60,paint);
    }

    public void drawMe(Canvas canvas) {
        paint2.setColor(Color.rgb(100, 100, 100));
        int size = 10;
        RectF rect = new RectF(
                xy.getX() - size,
                xy.getY() - size,
                xy.getX() + size,
                xy.getY() + size
        );
        if(arc != 0) {
            Matrix m = new Matrix();
            // point is the point about which to rotate.
            m.setRotate(arc, xyDevice.getX(), xyDevice.getY());
            m.mapRect(rect);

            xy.setX((rect.left + rect.right) / 2);
            xy.setY((rect.top + rect.bottom) / 2);
            arc = 0;
        }

        canvas.drawRect(rect,paint2);
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

    public void setRadius(float radius) {
        this.radius = radius;

    }

    public Paint getPaint2() {
        return paint2;
    }

    public void setPaint2(Paint paint2) {
        this.paint2 = paint2;
    }

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }

    public void upScore(){
        score ++;
    }

    public void downScore() {
        score--;
    }

    public void upLevel() {
        level++;
    }
}
