package com.sandeep.kattis;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelbrotFractalViz extends JPanel {

    /**
     * Default serial id
     */
    private static final long serialVersionUID = 1L;
    static final int COUNT = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (double i = -2.0; i < 2.0; i += 0.01) {
            for (double j = -2.0; j < 2.0; j += 0.01) {
                int count = count(new Complex(i, j));

                if (count == COUNT) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(new Color(count * 76 % 125, (count * 51) % 256, (count * 93) % 256));
                }

                g.drawRect((int) (i * 100) + 200, (int) (j * 100) + 200, 1, 1);
            }
        }
    }

    static int count(Complex c) {
        Complex z = new Complex(0, 0);

        for (int i = 0; i < COUNT; i++) {
            z = z.sqaure().add(c);
            if (z.abs() > 2) {
                return i;
            }
        }
        return COUNT;
    }

    public static void main(String[] args) {
        MandelbrotFractalViz panel = new MandelbrotFractalViz();
        JFrame frame = new JFrame("Vandelbrot Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(1, 1));
        frame.add(panel);
        frame.setSize(400, 425);
        frame.setVisible(true);
    }
}