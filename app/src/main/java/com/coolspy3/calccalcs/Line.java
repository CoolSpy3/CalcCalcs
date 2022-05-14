package com.coolspy3.calccalcs;

public class Line
{

    public final double m, b;

    public Line(double m, double b)
    {
        this.m = m;
        this.b = b;
    }

    public Line(double m, double x1, double y1)
    {
        this.m = m;
        this.b = y1 - m * x1;
    }

    public double evaluateAt(double x)
    {
        return m * x + b;
    }

}
