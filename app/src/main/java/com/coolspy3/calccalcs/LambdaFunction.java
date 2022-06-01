package com.coolspy3.calccalcs;

import java.util.function.DoubleUnaryOperator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

public class LambdaFunction extends Function
{

    private final Interface func;

    public LambdaFunction(String name, Interface func)
    {
        super(name);
        this.func = func;
    }

    public LambdaFunction(String name, int numArguments, Interface func)
    {
        super(name, numArguments);
        this.func = func;
    }

    public static Expression expressionFromLambda(DoubleUnaryOperator func)
    {
        return new ExpressionBuilder("func(x)").variable("x")
                .function(new LambdaFunction("func", args -> func.applyAsDouble(args[0]))).build();
    }

    @Override
    public double apply(double... args)
    {
        return func.apply(args);
    }

    public static interface Interface
    {
        public double apply(double... args);
    }

}
