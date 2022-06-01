package com.coolspy3.calccalcs.calcs;

import com.coolspy3.calccalcs.ImageBuffer;

public class WaterBottleCalculator extends Calculator<ImageBuffer>
{
    public WaterBottleCalculator()
    {
        super(ImageBuffer.NO_IMAGE, "Radius (Length): ", "Height (Length): ", "Angle (Degrees): ",
                "Rate (Volume / Time): ");
        customResults(true);
    }

    @Override
    protected double calculate(String[] args)
    {
        double r = Double.parseDouble(args[0]);
        double h = Double.parseDouble(args[1]);
        double theta = Double.parseDouble(args[2]);
        double dV_dT = -Double.parseDouble(args[3]);
        if (theta <= 0 || theta >= 90)
            throw new IllegalArgumentException("theta must be in (0, 90) degrees");
        theta = Math.toRadians(theta);
        double v = WaterBottleMath.v(r, h, theta);
        double dTheta_dT = dV_dT / WaterBottleMath.dV_dTheta(r, h, theta);
        resultField.setText(String.format("v: %f, d\u03b8/dt: %f", v, dTheta_dT));
        return 0;
    }

}
