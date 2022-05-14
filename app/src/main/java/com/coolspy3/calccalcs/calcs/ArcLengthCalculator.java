package com.coolspy3.calccalcs.calcs;

import com.coolspy3.calccalcs.Graph;
import com.coolspy3.calccalcs.LambdaFunction;
import com.coolspy3.calccalcs.Utils;

import net.objecthunter.exp4j.ExpressionBuilder;

public class ArcLengthCalculator extends Calculator<Graph>
{

    public ArcLengthCalculator()
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
        return Utils.integrate(
                new ExpressionBuilder("sqrt(1+(dydx(x))^2)").variable("x")
                        .function(new LambdaFunction("dydx", lambdaArgs -> Utils
                                .differentiate(image.getExpression(), lambdaArgs[0])))
                        .build(),
                a, b, n);
    }

}
