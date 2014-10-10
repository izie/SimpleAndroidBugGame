package com.MattLAB.BugGame.controller;

import android.graphics.Canvas;
import com.MattLAB.BugGame.model.Ball;
import com.MattLAB.BugGame.model.CPoint;
import com.MattLAB.BugGame.model.Me;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by changmatthew on 10/9/14.
 */
public class MeManager {
    public static final int GAME_STATE_NORMAL =  0;
    public static final int GAME_STATE_WIN =  1;
    public static final int GAME_STATE_LOSE =  2;

    private Me me;
    private List<Ball> ballList;

    public Me getMe() {
        return me;
    }

    public MeManager() {
        me = new Me();
        ballList = new CopyOnWriteArrayList<Ball>();
    }

    public void setDefault(CPoint xyDevice, float radiusBg) {
        me.setXyDevice(xyDevice);
        me.setRadius(radiusBg);

        // Set Default XY in me
        CPoint point = new CPoint(xyDevice.getX(), xyDevice.getY() - radiusBg*2);
        me.setXy(point);

    }

    public void clear() {
        ballList.clear();
        // Set Default XY in me
        CPoint point = new CPoint(me.getXyDevice().getX(), me.getXyDevice().getY() - me.getRadius()*2);
        me.setXy(point);
    }

    public void setMe(Me me) {
        this.me = me;
    }

    public List<Ball> getBallList() {

        return ballList;
    }

    public void setBallList(List<Ball> ballList) {
        this.ballList = ballList;
    }

    public void drawAll(Canvas canvas) {
        me.drawBackground(canvas);
        me.drawMe(canvas);
        me.drawDashboard(canvas);

        for(Ball ball : ballList) {
            ball.draw(canvas);
        }
    }

    public void update() {
        int k = 0;
        for(Ball ball : ballList) {
            if(ball.getXy().getX() < 0 || ball.getXy().getY() < 0)
                continue;
            ball.update(me.getXy());
        }

        for(Ball ball : ballList) {
            if (ball.getXy().getX() > 0 && ball.getXy().getY() > 0)
                break;
            k++;
        }
        if(k == ballList.size()){
            ballList.clear();
        }

    }


    public void rotate(float arc){
        me.setArc(arc);
        //Log.d("point2","x : "+point.getX() + "y : "+point.getY());
    }

    public void addBall() {
        Ball ball = new Ball(new CPoint(me.getXy().getX(), me.getXy().getY()), me.getXyDevice());
        ballList.add(ball);
    }

    public void restart() {
        me.setScore(0);
        me.setLevel(1);
        me.setMsg("");
        me.setWin_or_lose(GAME_STATE_NORMAL);
    }

}
