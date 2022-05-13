package com.coolspy3.calccalcs;

import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class GUI extends JFrame {

    public GUI() {
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Graph graph = new Graph();
        graph.setSize(300, 300);
        graph.setDomain(0, Math.PI * 2);
        graph.reset("sin(x)");
        graph.addLine(new Line(1, 0));
        graph.addShape(new Rectangle2D.Double(0, 0, 1, 1));
        add(graph);
        setVisible(true);
        graph.updateImage();
        graph.repaint();
    }

}
