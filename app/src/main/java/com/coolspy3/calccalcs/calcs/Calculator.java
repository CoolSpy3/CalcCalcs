package com.coolspy3.calccalcs.calcs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.coolspy3.calccalcs.ImageBuffer;

public abstract class Calculator<T extends ImageBuffer> extends JPanel implements ActionListener
{

    protected final T image;
    protected final JPanel controlPanel;
    protected final JTextField[] controls;
    protected final JTextField resultField;
    protected final JButton calculateButton;
    private boolean customResults = false;

    public Calculator(T image, String... controls)
    {
        super(new BorderLayout());
        this.image = image;
        this.controls = new JTextField[controls.length];
        this.controlPanel = new JPanel(new GridLayout(controls.length + 1, 2));
        for (int i = 0; i < controls.length; i++)
        {
            controlPanel.add(new JLabel(controls[i]));
            controlPanel.add(this.controls[i] = new JTextField(80));
        }
        controlPanel.add(new JLabel("Result:"));
        controlPanel.add(resultField = new JTextField(80));
        resultField.setEditable(false);

        add(controlPanel, BorderLayout.NORTH);
        add(image, BorderLayout.CENTER);
        add(calculateButton = new JButton("Calculate"), BorderLayout.SOUTH);
        calculateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == calculateButton)
        {
            double result;
            try
            {
                result = calculate(getArgs());
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.err);
                onInvalidArgs();
                return;
            }
            if (image.updateImage())
            {
                onInvalidArgs();
                return;
            }
            image.repaint();
            if (!customResults) resultField.setText(Double.toString(result));
        }
    }

    protected void customResults(boolean customResults)
    {
        this.customResults = customResults;
    }

    private String[] getArgs()
    {
        String[] args = new String[controls.length];
        for (int i = 0; i < controls.length; i++)
            args[i] = controls[i].getText();
        return args;
    }

    private void onInvalidArgs()
    {
        resultField.setText("Error!");
        JOptionPane.showMessageDialog(null, "Invalid Arguments!", "Error!",
                JOptionPane.ERROR_MESSAGE);
    }

    protected abstract double calculate(String[] args);

}
