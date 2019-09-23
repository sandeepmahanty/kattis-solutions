package com.sandeep.kattis;

import java.util.Scanner;

/**
 * This is a solution for the Mandlebrot Problem from {@code Kattis}
 * 
 * @see <a href="https://open.kattis.com/problems/mandelbrot"> Mandlebrot
 *      Problem</a>
 */
public class MandelBrot {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int cases = 1;

        while (sc.hasNextLine()) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            int limit = sc.nextInt();
            sc.nextLine();
            Complex z = new Complex(0, 0);
            Complex c = new Complex(x, y);
            boolean found = false;

            for (int i = 0; i < limit; i++) {
                z = z.sqaure().add(c);
                if (z.abs() > 2) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Case " + cases + ": IN");
            } else {
                System.out.println("Case " + cases + ": OUT");
            }
            cases++;
        }
        sc.close();
    }
}

class Complex {
    private double x;
    private double y;

    public Complex add(Complex comp) {
        this.x = this.x + comp.x;
        this.y = this.y + comp.y;
        return this;
    }

    public Complex sqaure() {
        double y = 2 * this.x * this.y;
        double x = this.x * this.x - this.y * this.y;
        this.x = x;
        this.y = y;
        return this;
    }

    public double abs() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Complex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Complex [" + x + "," + y + "i]";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}