package com.coolspy3.calccalcs;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.coolspy3.calccalcs.calcs.ArcLengthCalculator;
import com.coolspy3.calccalcs.calcs.EulerMethodCalculator;
import com.coolspy3.calccalcs.calcs.RRCalculator;
import com.coolspy3.calccalcs.calcs.TrapezoidRuleCalculator;

public class GUI extends JFrame
{

    public GUI()
    {
        super("Calc Calcs");

        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane calcs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        calcs.addTab("RR", new RRCalculator());
        calcs.addTab("Trapezoid Rule", new TrapezoidRuleCalculator());
        calcs.addTab("Euler's Method", new EulerMethodCalculator());
        calcs.addTab("Arc Length", new ArcLengthCalculator());

        add(calcs);
        setVisible(true);
    }

}
