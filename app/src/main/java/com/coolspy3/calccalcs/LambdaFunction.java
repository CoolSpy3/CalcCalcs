package com.coolspy3.calccalcs;

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
