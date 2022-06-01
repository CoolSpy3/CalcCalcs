package com.coolspy3.calccalcs.calcs;

import static java.lang.Math.PI;
import static java.lang.Math.acos;
import static java.lang.Math.atan;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

import com.coolspy3.calccalcs.LambdaFunction;
import com.coolspy3.calccalcs.Utils;

public final class WaterBottleMath
{

    public static double v(double r, double h, double theta)
    {
        double tan = tan(theta), cot = 1 / tan;
        if (theta > atan(2 * r / h))
        {
            return PI * pow(r, 2) * h - PI * pow(r, 3) * cot;
        }
        else if (theta < atan(r / h))
        {
            return Utils.integrate(
                    LambdaFunction.expressionFromLambda(h1 -> pow(r, 2) * acos(1 - h1 * tan / r)
                            - (r - h1 * tan) * sqrt(2 * h1 * r * tan - pow(h1 * tan, 2))),
                    0, h, 1000);
        }
        return Utils.integrate(
                LambdaFunction.expressionFromLambda(h1 -> pow(r, 2) * acos(1 - h1 * tan / r)
                        - (r - h1 * tan) * sqrt(2 * h1 * r * tan - pow(h1 * tan, 2))),
                0, r * cot, 1000)
                + Utils.integrate(
                        LambdaFunction.expressionFromLambda(
                                h1 -> PI * pow(r, 2) - pow(r, 2) * acos(h1 * tan - 1)
                                        + (h * tan - r)
                                                * sqrt(2 * h1 * r * tan - pow(h1 * tan, 2))),
                        r * cot, h, 1000);
    }

    public static double dV_dTheta(double r, double h, double theta)
    {
        return Utils.differentiate(LambdaFunction.expressionFromLambda(theta1 -> v(r, h, theta1)),
                theta);
    }

    // public static double v(double r, double h, double theta)
    // {
    // double tan = tan(theta), cot = 1 / tan;
    // double hTanThetaOverRMinus1 = h * tan / r - 1;
    // double sqrtTerm = sqrt(2 * h * tan / r - pow(h * tan / r, 2));
    // double correction = (cot / 3) * pow(2 * r * h * tan - pow(h * tan, 2), 3 / 2);
    // if (theta > atan(2 * r / h))
    // {
    // return PI * pow(r, 2) * h - PI * pow(r, 3) * cot;
    // }
    // else if (theta < atan(r / h))
    // {
    // return pow(r, 3) * cot
    // * (hTanThetaOverRMinus1 * acos(-hTanThetaOverRMinus1) + sqrtTerm - 1)
    // - correction;
    // }
    // return PI * pow(r, 2) * (h - r * cot)
    // + pow(r, 3) * cot * (-hTanThetaOverRMinus1 * acos(hTanThetaOverRMinus1) + sqrtTerm)
    // - correction;
    // }

    // public static double dV_dTheta(double r, double h, double theta)
    // {
    // double sin = sin(theta), cos = cos(theta), tan = tan(theta), csc = 1 / sin, sec = 1 /
    // cos,
    // cot = 1 / tan;
    // double r3csc2 = pow(r, 3) * pow(csc, 2);
    // double r3cot = pow(r, 3) * cot;
    // double hTanThetaOverRMinus1 = h * tan / r - 1;
    // double hSec2OverR = h * pow(sec, 2) / r;
    // double sqrtTerm = sqrt(2 * h * tan / r - pow(h * tan / r, 2));
    // double twoRHTanMinusHTan2 = 2 * r * h * tan - pow(h * tan, 2);
    // double correction = (pow(csc, 2) / 3) * pow(twoRHTanMinusHTan2, 3 / 2)
    // - h * csc * sec * (r - h * tan) * sqrt(twoRHTanMinusHTan2);
    // if (theta > atan(2 * r / h))
    // {
    // return PI * r3csc2;
    // }
    // else if (theta < atan(r / h))
    // {
    // return r3csc2 * (1 + hTanThetaOverRMinus1 * acos(hTanThetaOverRMinus1) - sqrtTerm)
    // + r3cot * (hSec2OverR * acos(-hTanThetaOverRMinus1)
    // + (pow(h * tan / r, 2) - h * tan / r) * (1 / sqrtTerm)
    // + hSec2OverR * (1 - h * tan) * (1 / sqrtTerm))
    // + correction;
    // }
    // return r3csc2 * (PI + hTanThetaOverRMinus1 * acos(hTanThetaOverRMinus1) - sqrtTerm)
    // + r3cot * (hSec2OverR * (-hTanThetaOverRMinus1) * (1 / sqrtTerm)
    // - hSec2OverR * acos(-hTanThetaOverRMinus1)
    // + (pow(h * tan / r, 2) - h * tan / r) * (1 / sqrtTerm))
    // + correction;
    // }

    private WaterBottleMath()
    {}

}
