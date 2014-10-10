package com.MattLAB.BugGame.controller;

import android.graphics.Canvas;
import android.util.Log;
import com.MattLAB.BugGame.model.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by changmatthew on 10/9/14.
 */
public class BugManager {
    public static final int GAME_STATE_NORMAL =  0;
    public static final int GAME_STATE_WIN =  1;
    public static final int GAME_STATE_LOSE =  2;

    CPoint xyDevice;

    private List<CircleBug> circleBug = new CopyOnWriteArrayList<CircleBug>();
    private List<RectBug> rectBug = new CopyOnWriteArrayList<RectBug>();
    private List<TriBug> triBug = new CopyOnWriteArrayList<TriBug>();

    private float radiusBg;

    public BugManager() {
    }

    // Set Default Values
    public void setDefaultBugs(
            int numCircleBug, int numRectBug, int numTriBug,
            int limitRadiusTriBug, int limitRadiusRectBug, int limitRadiusCircle,
            CPoint xyDevice, float radiusBg
    ){

        this.radiusBg = radiusBg;
        this.xyDevice = xyDevice;

        for(int i = 0 ; i < numTriBug ; i++){
            TriBug triBug1 = new TriBug();
            triBug1.setXyDevice(xyDevice);
            triBug1.setPosition(getRandomXY(0, limitRadiusTriBug));

            triBug.add(triBug1);
        }



        for(int i = 0 ; i < numRectBug ; i++){
            RectBug rectBug1 = new RectBug();
            rectBug1.setXyDevice(xyDevice);
            rectBug1.setPosition(getRandomXY(limitRadiusTriBug, limitRadiusRectBug));

            rectBug.add(rectBug1);
        }

        for(int i = 0 ; i < numCircleBug ; i++){
            CircleBug circleBug1 = new CircleBug();
            circleBug1.setXyDevice(xyDevice);
            circleBug1.setPosition(getRandomXY(limitRadiusRectBug, limitRadiusCircle));

            circleBug.add(circleBug1);
        }


    }

    public void clearBugs(
            int numCircleBug, int numRectBug, int numTriBug,
            int limitRadiusTriBug, int limitRadiusRectBug, int limitRadiusCircle
    ){

        triBug.clear();
        rectBug.clear();
        circleBug.clear();

        for(int i = 0 ; i < numTriBug ; i++){
            TriBug triBug1 = new TriBug();
            triBug1.setXyDevice(xyDevice);
            triBug1.setPosition(getRandomXY(0, limitRadiusTriBug));

            triBug.add(triBug1);
        }



        for(int i = 0 ; i < numRectBug ; i++){
            RectBug rectBug1 = new RectBug();
            rectBug1.setXyDevice(xyDevice);
            rectBug1.setPosition(getRandomXY(limitRadiusTriBug, limitRadiusRectBug));

            rectBug.add(rectBug1);
        }

        for(int i = 0 ; i < numCircleBug ; i++){
            CircleBug circleBug1 = new CircleBug();
            circleBug1.setXyDevice(xyDevice);
            circleBug1.setPosition(getRandomXY(limitRadiusRectBug, limitRadiusCircle));

            circleBug.add(circleBug1);
        }


    }
    
    public CPoint getRandomXY(int minRadius, int maxRadius) {
        float tx;
        float ty;

        Random generator = new Random();

        while(true){
            tx = generator.nextFloat()*2000 - generator.nextFloat()*2000;
            ty = generator.nextFloat()*2000 - generator.nextFloat()*2000;

            double size = Math.sqrt(Math.pow(tx,2) + Math.pow(ty, 2));

            if((minRadius < size && size < maxRadius)){
                break;
            }
        }
        CPoint xy = new CPoint(tx + xyDevice.getX(),ty + xyDevice.getY());

        return xy;
    }



    public void drawAll(Canvas canvas) {

        for(TriBug triBug1 : triBug){
            triBug1.draw(canvas);
        }
        for(RectBug rectBug1 : rectBug){
            rectBug1.draw(canvas);
        }
        for(CircleBug circleBug1 : circleBug){
            circleBug1.draw(canvas);
        }
    }

    public void update(MeManager me, int speed) {
        // 방향에 따라 이동
        Random generator = new Random();
        for(TriBug triBug1 : triBug){
            //triBug1.draw(canvas);
            if(!triBug1.isHeated() && isCollisionWithBg(triBug1.getXy())){
                me.getMe().setWin_or_lose(GAME_STATE_LOSE);
            }
            
        }
        for(RectBug rectBug1 : rectBug){
            //rectBug1.draw(canvas);
            if(!rectBug1.isHeated() && isCollisionWithBg(rectBug1.getXy())){
                me.getMe().setWin_or_lose(GAME_STATE_LOSE);
            }
            if(rectBug1.getXy().getX() < 0 || rectBug1.getXy().getY() < 0)  continue;
            int x = generator.nextInt(speed);
            int y = generator.nextInt(speed);

            if(me.getMe().getXy().getX() > rectBug1.getXy().getX()){
                rectBug1.getXy().setX(rectBug1.getXy().getX() + x);
            }else if(me.getMe().getXy().getX() < rectBug1.getXy().getX()){
                rectBug1.getXy().setX(rectBug1.getXy().getX() - x);
            }
            if(me.getMe().getXy().getY() > rectBug1.getXy().getY()){
                rectBug1.getXy().setY(rectBug1.getXy().getY() + y);
            }else if(me.getMe().getXy().getY() < rectBug1.getXy().getY()){
                rectBug1.getXy().setY(rectBug1.getXy().getY() - y);
            }
        }
        for(CircleBug circleBug1 : circleBug){
            //circleBug1.draw(canvas);
            //rectBug1.draw(canvas);
            if(!circleBug1.isHeated() && isCollisionWithBg(circleBug1.getXy())){
                me.getMe().setWin_or_lose(GAME_STATE_LOSE);
            }
            if(circleBug1.getXy().getX() < 0 || circleBug1.getXy().getY() < 0)  continue;
            int x = generator.nextInt(speed);
            int y = generator.nextInt(speed);

            if(me.getMe().getXy().getX() > circleBug1.getXy().getX()){
                circleBug1.getXy().setX(circleBug1.getXy().getX() + x);
            }else if(me.getMe().getXy().getX() < circleBug1.getXy().getX()){
                circleBug1.getXy().setX(circleBug1.getXy().getX() - x);
            }
            if(me.getMe().getXy().getY() > circleBug1.getXy().getY()){
                circleBug1.getXy().setY(circleBug1.getXy().getY() + y);
            }else if(me.getMe().getXy().getY() < circleBug1.getXy().getY()){
                circleBug1.getXy().setY(circleBug1.getXy().getY() - y);
            }
        }

        checkCollision(me);
    }

    private void checkCollision(MeManager me) {
        int boundary = (int)(xyDevice.getX()*0.1);
        synchronized (me.getBallList()){


            for(Ball ball : me.getBallList()){
                boolean heated = false;
                for(TriBug triBug1 : triBug){

                    if( !ball.isHeated() &&
                            !triBug1.isHeated() &&
                            (
                                    (triBug1.getXy().getX() > ball.getXy().getX() - boundary) &&
                                            (triBug1.getXy().getX() < ball.getXy().getX() + boundary)
                            ) &&
                            (
                                    (triBug1.getXy().getY() > ball.getXy().getY() - boundary) &&
                                            (triBug1.getXy().getY() < ball.getXy().getY() + boundary)
                            )
                            ){
                        me.getMe().upScore();
                        triBug1.setHeated(true);
                        ball.setHeated(true);
                        Log.d("heated","heated");
                        heated = true;
                        break;
                    }

                }
                if(heated)  continue;
                for(RectBug rectBug1 : rectBug){

                    if( !ball.isHeated() &&
                            !rectBug1.isHeated() &&
                            (
                                    (rectBug1.getXy().getX() > ball.getXy().getX() - boundary) &&
                                            (rectBug1.getXy().getX() < ball.getXy().getX() + boundary)
                            ) &&
                            (
                                    (rectBug1.getXy().getY() > ball.getXy().getY() - boundary) &&
                                            (rectBug1.getXy().getY() < ball.getXy().getY() + boundary)
                            )
                            ){
                        me.getMe().upScore();
                        rectBug1.setHeated(true);
                        ball.setHeated(true);
                        Log.d("heated","heated");
                        heated = true;
                        break;
                    }
                }
                if(heated)  continue;
                for(CircleBug circleBug1 : circleBug){

                    if( !ball.isHeated() &&
                            !circleBug1.isHeated() &&
                            (
                                    (circleBug1.getXy().getX() > ball.getXy().getX() - boundary) &&
                                            (circleBug1.getXy().getX() < ball.getXy().getX() + boundary)
                            ) &&
                            (
                                    (circleBug1.getXy().getY() > ball.getXy().getY() - boundary) &&
                                            (circleBug1.getXy().getY() < ball.getXy().getY() + boundary)
                            )
                            ){
                        me.getMe().upScore();
                        circleBug1.setHeated(true);
                        ball.setHeated(true);
                        heated = true;
                        Log.d("heated","heated");
                        break;
                    }
                }
            }
        }


    }

    public boolean isAllClear() {
        boolean heated = true;
        for (TriBug triBug1 : triBug) {
            if (!triBug1.isHeated()) {
                heated = false;
                break;
            }

        }
        if (heated){
            for (RectBug rectBug1 : rectBug) {

                if(!rectBug1.isHeated()){
                    heated = false;
                    break;
                }
            }
        }
        if(heated){
            for(CircleBug circleBug1 : circleBug){
                if(!circleBug1.isHeated()){
                    heated = false;
                    break;
                }
            }
        }

        return heated;

    }

    public boolean isCollisionWithBg(CPoint xy) {
        double size = Math.sqrt(Math.pow(xy.getX() - xyDevice.getX(),2) + Math.pow(xy.getY() - xyDevice.getY(), 2));

        if(size < radiusBg*2) return false;
        else{
            Log.d("collision!!","col!! x : +"+(xy.getX() - xyDevice.getX())+"   y : "+(xy.getY() - xyDevice.getY())+"   size: "+size+"   radi : "+radiusBg);
            return true;
        }
    }


}
