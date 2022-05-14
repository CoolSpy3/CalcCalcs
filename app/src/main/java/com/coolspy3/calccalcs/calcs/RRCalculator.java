package com.coolspy3.calccalcs.calcs;

import com.coolspy3.calccalcs.Graph;
import com.coolspy3.calccalcs.Utils;

public class RRCalculator extends Calculator<Graph>
{

    public RRCalculator()
    {
        super(new Graph(), "Function: ", "a: ", "b: ", "n: ");
    }

    @Override
    protected double calculate(String[] args)
    {
        image.reset(args[0]);
        double a = Double.parseDouble(args[1]);
        double b = Double.parseDouble(args[2]);
        int n = Integer.parseInt(args[3]);
        image.setDomain(a, b);
        double dx = (b - a) / n;
        double sum = 0;
        for (double x = a + dx, i = 0; i < n; x += dx, i++)
        {
            double val = image.evaluateAt(x);
            sum += val;
            image.addShape(Utils.createRectangle(x - dx, 0, dx, val));
        }
        return dx * sum;
    }

}
