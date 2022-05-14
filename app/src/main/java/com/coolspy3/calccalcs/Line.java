package com.coolspy3.calccalcs;

public class Line
{

    public final double m, b;

    public Line(double m, double b)
    {
        this.m = m;
        this.b = b;
    }

    public double evaluateAt(double x)
    {
        return m * x + b;
    }

}
