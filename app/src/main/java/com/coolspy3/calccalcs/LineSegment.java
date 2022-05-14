package com.coolspy3.calccalcs;

public class LineSegment extends Line
{

    public final double xMin, xMax;

    public LineSegment(double m, double b, double x1, double x2)
    {
        super(m, b);
        this.xMin = Math.min(x1, x2);
        this.xMax = Math.max(x1, x2);
    }

    public LineSegment(double m, double x0, double y0, double x1, double x2)
    {
        super(m, x0, y0);
        this.xMin = Math.min(x1, x2);
        this.xMax = Math.max(x1, x2);
    }

}
