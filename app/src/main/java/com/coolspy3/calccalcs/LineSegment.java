package com.coolspy3.calccalcs;

public class LineSegment extends Line {

    public final double xMin, xMax;

    public LineSegment(double m, double b, double xMin, double xMax) {
        super(m, b);
        this.xMin = xMin;
        this.xMax = xMax;
    }

}
