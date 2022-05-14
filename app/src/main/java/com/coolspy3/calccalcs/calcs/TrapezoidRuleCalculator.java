package com.coolspy3.calccalcs.calcs;

import com.coolspy3.calccalcs.Graph;
import com.coolspy3.calccalcs.Polygon2D;

public class TrapezoidRuleCalculator extends Calculator<Graph>
{

    public TrapezoidRuleCalculator()
    {
        super(new Graph(), "Function: ", "a: ", "b: ", "n: ");
    }

    @Override
    protected double calculate(String[] args)
    {
        image.reset(args[0]);
        double a = Double.parseDouble(args[1]);
        double b = Double.parseDouble(args[2]);
        double n = Double.parseDouble(args[3]);
        image.setDomain(a, b);
        double dx = (b - a) / n;
        double sum = 0;
        double nextVal = image.evaluateAt(a);
        for (double x = a, i = 0; i < n; x += dx, i++)
        {
            double val = nextVal;
            double nextX = x + dx;
            nextVal = image.evaluateAt(nextX);
            sum += val + nextVal;
            image.addShape(new Polygon2D(new double[] {x, x, nextX, nextX},
                    new double[] {0, val, nextVal, 0}));
        }
        return 0.5 * dx * sum;
    }

}
