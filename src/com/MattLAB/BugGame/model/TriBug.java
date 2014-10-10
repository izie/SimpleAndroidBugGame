package com.MattLAB.BugGame.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

/**
 * Created by changmatthew on 10/9/14.
 */
public class TriBug extends Bug {
    Path path;
    private final int degree_of_triangle = 60;
    private final float radian_of_triangle = (float) Math.toRadians(degree_of_triangle);

    public TriBug() {
        super();
        path = new Path();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(!isHeated()){
            setColor(Color.GREEN);
            int TriangleSize = (int)(getXyDevice().getX()*0.1);;
            float x = getXy().getX();
            float y = getXy().getY();

            path.moveTo(x, y - TriangleSize/2);
            path.rLineTo( -TriangleSize * (float) Math.cos(radian_of_triangle), +TriangleSize * (float) Math.sin(radian_of_triangle));
            path.rLineTo(+TriangleSize, 0);
            path.rLineTo(-TriangleSize/2, -TriangleSize * (float) Math.sin(radian_of_triangle));
            path.rLineTo( -TriangleSize* (float) Math.cos(radian_of_triangle), +TriangleSize * (float) Math.sin(radian_of_triangle));
            path.close();

            canvas.drawPath(path, getPaint());
        }

    }
}
