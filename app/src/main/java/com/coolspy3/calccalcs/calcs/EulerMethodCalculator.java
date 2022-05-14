package com.coolspy3.calccalcs.calcs;

import com.coolspy3.calccalcs.Graph;
import com.coolspy3.calccalcs.LambdaFunction;
import com.coolspy3.calccalcs.LineSegment;
import com.coolspy3.calccalcs.Utils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class EulerMethodCalculator extends Calculator<Graph>
{

    public EulerMethodCalculator()
    {
        super(new Graph(), "dy/dx= ", "x1: ", "y1: ", "x2: ", "n: ");
    }

    @Override
    protected double calculate(String[] args)
    {
        double x1 = Double.parseDouble(args[1]);
        double y1 = Double.parseDouble(args[2]);
        double x2 = Double.parseDouble(args[3]);
        int n = Integer.parseInt(args[4]);
        Expression diffEq = new ExpressionBuilder(args[0]).variables("x", "y").build();
        image.reset(
                new ExpressionBuilder("func(x)").variable("x")
                        .function(new LambdaFunction("func",
                                lambdaArgs -> Utils.eulerMethod(diffEq, x1, y1, lambdaArgs[0])))
                        .build());
        image.setDomain(x1, x2);
        double dx = (x2 - x1) / n;
        double x = x1, y = y1;
        for (int i = 0; i < n; i++)
        {
            double dydx = diffEq.setVariable("x", x).setVariable("y", y).evaluate();
            double ny = y + dx * dydx;
            image.addLineSegment(new LineSegment(dydx, x, y, x, x += dx));
            y = ny;
        }
        return y;
    }

}
