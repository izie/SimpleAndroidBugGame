package com.MattLAB.BugGame.model;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by changmatthew on 10/9/14.
 */
public class CircleBug extends Bug {
    private float radius;
    public CircleBug() {
        super();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if(!isHeated()){
            radius = (float)(getXyDevice().getX()*0.02);
            setColor(Color.BLUE);
            canvas.drawCircle(
                    getXy().getX()- radius,
                    getXy().getY()- radius,
                    radius * 2,
                    getPaint()
            );
        }

    }
}
