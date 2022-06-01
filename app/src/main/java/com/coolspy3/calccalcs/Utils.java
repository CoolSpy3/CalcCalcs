package com.coolspy3.calccalcs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import net.objecthunter.exp4j.Expression;

public final class Utils
{

    public static Rectangle2D createRectangle(double x, double y, double w, double h)
    {
        if (w < 0)
        {
            x = x + w;
            w = -w;
        }
        if (h < 0)
        {
            y = y + h;
            h = -h;
        }
        return new Rectangle2D.Double(x, y, w, h);
    }

    public static double differentiate(Expression expr, double x)
    {
        double epsilon = 0.0001;
        double x0 = x - epsilon, x1 = x, x2 = x + epsilon;
        return (((evaluateAt(x1, expr) - evaluateAt(x0, expr)) / (x1 - x0))
                + ((evaluateAt(x2, expr) - evaluateAt(x1, expr)) / (x2 - x1))) / 2;
    }

    public static void drawLine(double x1, double y1, double x2, double y2, Graphics2D g)
    {
        drawLine(x1, y1, x2, y2, g, new AffineTransform());
    }

    public static void drawLine(double x1, double y1, double x2, double y2, Graphics2D g,
            AffineTransform transform)
    {
        Point2D p1 = transform.transform(new Point2D.Double(x1, y1), null);
        Point2D p2 = transform.transform(new Point2D.Double(x2, y2), null);
        g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    public static double eulerMethod(Expression diffEq, double x1, double y1, double x2)
    {
        int n = 1000;
        double dx = (x2 - x1) / n;
        double x = x1, y = y1;
        for (int i = 0; i < n; x += dx, i++)
        {
            double dydx = diffEq.setVariable("x", x).setVariable("y", y).evaluate();
            y += dydx * dx;
        }
        return y;
    }

    public static double evaluateAt(double x, Expression expression)
    {
        return expression.setVariable("x", x).evaluate();
    }

    public static double integrate(Expression expr, double a, double b, double n)
    {
        double dx = (b - a) / n;
        double sum = evaluateAt(a, expr) + evaluateAt(b, expr);
        sum *= .5;
        double x = a + dx;
        for (int i = 0; i < n - 1; x += dx, i++)
        {
            sum += evaluateAt(x, expr);
        }
        sum *= dx;
        return sum;
    }

    public static double minOn(double xMin, double xMax, Expression expression, double dx)
    {
        double min = evaluateAt(xMin, expression);
        for (double x = xMin; x <= xMax; x += dx)
        {
            min = Math.min(min, evaluateAt(x, expression));
        }
        return min;
    }

    public static double maxOn(double xMin, double xMax, Expression expression, double dx)
    {
        double max = evaluateAt(xMin, expression);
        for (double x = xMin; x <= xMax; x += dx)
        {
            max = Math.max(max, evaluateAt(x, expression));
        }
        return max;
    }

    private Utils()
    {}

}
