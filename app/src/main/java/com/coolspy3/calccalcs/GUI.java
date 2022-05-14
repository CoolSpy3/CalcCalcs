package com.coolspy3.calccalcs;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.coolspy3.calccalcs.calcs.RRCalculator;

public class GUI extends JFrame {

    public GUI() {
        super("Calc Calcs");

        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane calcs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        calcs.addTab("RR", new RRCalculator());

        add(calcs);
        setVisible(true);
    }

}
