package com.coolspy3.calccalcs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.sound.sampled.Line;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Graph extends ImageBuffer {

    public static double defaultDX = 0.1D;
    public double dx;
    private Expression expression;
    private double xMin, xMax, yMin, yMax;
    private ArrayList<Line> lines;
    private ArrayList<Shape> shapes;

    public Graph() {
        dx = defaultDX;
    }

    public void setDomain(double b1, double b2) {
        xMin = Math.min(b1, b2);
        xMax = Math.max(b1, b2);
    }

    public void reset(String newExpression) {
        reset(new ExpressionBuilder(newExpression).build());
    }

    public void reset(Expression newExpression) {
        expression = newExpression;
        lines.clear();
        shapes.clear();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public double evaluateAt(double x) {
        return Utils.evaluateAt(x, expression);
    }

    @Override
    public void render(Graphics2D g) {
        calculateRange();
        AffineTransform transform = g.getTransform();
        transformPlane(g);
        drawGraph(g);
        g.setTransform(transform);
    }

    private void calculateRange() {
        yMin = Utils.minOn(xMin, xMax, expression, dx);
        yMax = Utils.maxOn(xMin, xMax, expression, dx);
        for(Shape shape : shapes) {
            yMin = Math.min(yMin, shape.getBounds2D().getMinY());
            yMax = Math.min(yMax, shape.getBounds2D().getMaxY());
        }
    }

    private void transformPlane(Graphics2D g) {
        g.scale(getWidth() / (xMax - xMin), getHeight() / (yMax - yMin));
        g.translate(xMin, xMax);
    }

    private void drawGraph(Graphics2D g) {
        g.setColor(new Color(0, 0, 255));
        double val = evaluateAt(xMin);
        for(double x = xMin; x <= xMax; x += dx) {
            Utils.drawLine(x, x + dx, val, val = evaluateAt(x + dx), g);
        }
    }

}
