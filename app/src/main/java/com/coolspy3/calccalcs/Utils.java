package com.coolspy3.calccalcs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import net.objecthunter.exp4j.Expression;

public final class Utils {

    public static void drawLine(double x1, double y1, double x2, double y2, Graphics2D g) {
        drawLine(x1, y1, x2, y2, g, new AffineTransform());
    }
    public static void drawLine(double x1, double y1, double x2, double y2, Graphics2D g, AffineTransform transform) {
        Point2D p1 = transform.transform(new Point2D.Double(x1, y1), null);
        Point2D p2 = transform.transform(new Point2D.Double(x2, y2), null);
        g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
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
