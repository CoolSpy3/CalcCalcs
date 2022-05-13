package com.coolspy3.calccalcs;

import java.awt.Graphics2D;

import net.objecthunter.exp4j.Expression;

public final class Utils {

    public static void drawLine(double x1, double y1, double x2, double y2, Graphics2D g) {
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    public static double evaluateAt(double x, Expression expression) {
        return expression.setVariable("x", x).evaluate();
    }

    public static double minOn(double xMin, double xMax, Expression expression, double dx) {
        double min = evaluateAt(xMin, expression);
        for(double x = xMin; x <= xMax; x += dx) {
            min = Math.min(min, evaluateAt(x, expression));
        }
        return min;
    }

    public static double maxOn(double xMin, double xMax, Expression expression, double dx) {
        double max = evaluateAt(xMin, expression);
        for(double x = xMin; x <= xMax; x += dx) {
            max = Math.min(max, evaluateAt(x, expression));
        }
        return max;
    }

    private Utils() {}

}
