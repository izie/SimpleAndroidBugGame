package com.MattLAB.BugGame.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

/**
 * Created by changmatthew on 10/9/14.
 */
public class RectBug extends Bug {
    public RectBug() {
        super();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(!isHeated()){
            setColor(Color.RED);
            int size = (int)(getXyDevice().getX()*0.02);;
            RectF rect = new RectF(
                    getXy().getX() - size,
                    getXy().getY() - size,
                    getXy().getX() + size,
                    getXy().getY() + size
            );

            canvas.drawRect(rect,getPaint());
        }

    }
}
