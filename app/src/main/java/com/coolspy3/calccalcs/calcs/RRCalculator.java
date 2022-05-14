package com.coolspy3.calccalcs.calcs;

import java.awt.geom.Rectangle2D;

import com.coolspy3.calccalcs.Graph;

public class RRCalculator extends Calculator<Graph> {

    public RRCalculator() {
        super(new Graph(), "Function: ", "a: ", "b: ", "n: ");
    }

    @Override
    protected double calculate(String[] args) {
        image.reset(args[0]);
        double a = Double.parseDouble(args[1]);
        double b = Double.parseDouble(args[2]);
        double n = Double.parseDouble(args[3]);
        image.setDomain(a, b);
        double dx = (b - a) / n;
        double sum = 0;
        for(double x = a + dx; x <= b; x += dx) {
            double val = image.evaluateAt(x);
            sum += val * dx;
            image.addShape(new Rectangle2D.Double(x - dx, 0, dx, val));
        }
        return sum;
    }

}
