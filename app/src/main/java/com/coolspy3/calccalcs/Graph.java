package com.coolspy3.calccalcs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Graph extends ImageBuffer
{

    public static double defaultDX = 0.1D;
    public double dx = defaultDX;
    private Expression expression;
    private double xMin, xMax, yMin, yMax;
    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private AffineTransform transform = new AffineTransform();

    public void setDomain(double b1, double b2)
    {
        xMin = Math.min(b1, b2);
        xMax = Math.max(b1, b2);
    }

    public void reset(String newExpression)
    {
        reset(new ExpressionBuilder(newExpression).variable("x").build());
    }

    public void reset(Expression newExpression)
    {
        expression = newExpression;
        lines.clear();
        lineSegments.clear();
        shapes.clear();
    }

    public void addLine(Line line)
    {
        lines.add(line);
    }

    public void addLineSegment(LineSegment segment)
    {
        lineSegments.add(segment);
    }

    public void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    public double evaluateAt(double x)
    {
        return Utils.evaluateAt(x, expression);
    }

    @Override
    public void render(Graphics2D g)
    {
        calculateRange();
        transformPlane();
        drawAxes(g);
        drawGraph(g);
    }

    private void calculateRange()
    {
        yMin = Utils.minOn(xMin, xMax, expression, dx);
        yMax = Utils.maxOn(xMin, xMax, expression, dx);
        for (Shape shape : shapes)
        {
            yMin = Math.min(yMin, shape.getBounds2D().getMinY());
            yMax = Math.max(yMax, shape.getBounds2D().getMaxY());
        }
    }

    private void transformPlane()
    {
        transform.setToIdentity();
        transform.scale(getWidth() / (xMax - xMin), -getHeight() / (yMax - yMin));
        transform.translate(-xMin, yMin);
        System.out.println(yMin);
        System.out.println(-getHeight() / (yMax - yMin));
    }

    private void drawAxes(Graphics2D g)
    {
        g.setColor(new Color(0, 0, 0));
        Utils.drawLine(xMin, 0, xMax, 0, g, transform);
        Utils.drawLine(0, yMin, 0, yMax, g, transform);
    }

    private void drawGraph(Graphics2D g)
    {
        g.setColor(new Color(0, 0, 255));
        drawFunction(this::evaluateAt, g);
        ArrayList<Shape> transformedShapes = new ArrayList<>(shapes);
        transformedShapes.replaceAll(transform::createTransformedShape);
        g.setColor(new Color(100, 100, 255));
        for (Shape shape : transformedShapes)
            g.draw(shape);
        g.setColor(new Color(200, 200, 255, 100));
        for (Shape shape : transformedShapes)
            g.fill(shape);
        g.setColor(new Color(255, 0, 0));
        for (Line line : lines)
            drawFunction(line::evaluateAt, g);
        for (LineSegment segment : lineSegments)
            drawFunction(segment::evaluateAt, g, segment.xMin, segment.xMax);
    }

    private void drawFunction(DoubleUnaryOperator func, Graphics2D g)
    {
        drawFunction(func, g, xMin, xMax);
    }

    private void drawFunction(DoubleUnaryOperator func, Graphics2D g, double xMin, double xMax)
    {
        double val = func.applyAsDouble(xMin);
        for (double x = xMin; x <= xMax; x += dx)
        {
            Utils.drawLine(x, val, x + dx, val = func.applyAsDouble(x + dx), g, transform);
        }
    }

}
