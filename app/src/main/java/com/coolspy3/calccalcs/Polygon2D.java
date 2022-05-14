package com.coolspy3.calccalcs;

import java.awt.geom.Path2D;

public class Polygon2D extends Path2D.Double
{

    private static final long serialVersionUID = 3485951007026555495L;

    public Polygon2D(double[] xs, double[] ys)
    {
        moveTo(xs[0], ys[0]);
        for (int i = 1; i < xs.length; i++)
            lineTo(xs[i], ys[i]);
        lineTo(xs[0], ys[0]);
    }

}
