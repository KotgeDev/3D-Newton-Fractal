/**
 * The function used to create the Newton Fractal is x^3 - 1
 * Sets the z value of each matrix in an object to the number of
 * iterations it took to approximate a root.
 */

import java.util.ArrayList;

public class ApplyMethod {
    // Roots of the polynomial x^3 - 1
    final static Complex root1 = new Complex(1.0, 0.0);
    final static Complex root2 = new Complex(-0.5, Math.sqrt(3.0) / 2.0);
    final static Complex root3 = new Complex(-0.5, -Math.sqrt(3.0) / 2.0);
    public ArrayList<Matrix> object = new ArrayList<Matrix>();
    private double zoom;
    private double h_shift;
    private double v_shift;

    public ApplyMethod(ArrayList<Matrix> object, double zoom,
                       double h_shift, double v_shift) {
        this.object = object;
        this.zoom = zoom;
        this.h_shift = h_shift;
        this.v_shift = v_shift;

        // For every point (represented with a matrix) in the object
        for (Matrix point : object) {
            Complex z = approx_root(point);

            point.setMatrix(2, 0, z.getCount()*20);
        }
    }

    // Approximates a root of the function using the given complex number
    public Complex approx_root(Matrix p) {
        Complex z = new Complex(p.getMatrix()[0][0] / this.zoom + this.h_shift,
                p.getMatrix()[1][0] / this.zoom + this.v_shift);
        double prevB;
        double prevA;

        int count = 0;

        do {
            prevA = z.getA();
            prevB = z.getB();

            z.setA(real(z.getA(), z.getB()));
            z.setB(imaginary(z.getA(), z.getB()));

            count++;
            z.setCount(count);

        } while (Math.sqrt(
                Math.pow(z.getA() - prevA, 2) +
                        Math.pow(z.getB() - prevB, 2)) > 0.01);

        return z;
    }

    // Real part of the function to use newton's method
    public static double real(double a, double b) {
        double x = Math.pow(a, 3.0) - 3.0 * a * Math.pow(b, 2.0) - 1.0;
        double y = 3.0 * Math.pow(a, 2.0) * b - Math.pow(b, 3.0);
        double c = 3.0 * (Math.pow(a, 2.0) - Math.pow(b, 2.0));
        double d = 6.0 * a * b;
        return a - (x * c + y * d) / (Math.pow(c, 2.0) + Math.pow(d, 2.0));
    }

    // Imaginary part of the function to use newton's method
    public static double imaginary(double a, double b) {
        double x = Math.pow(a, 3.0) - 3.0 * a * Math.pow(b, 2.0) - 1.0;
        double y = 3.0 * Math.pow(a, 2.0) * b - Math.pow(b, 3.0);
        double c = 3.0 * (Math.pow(a, 2.0) - Math.pow(b, 2.0));
        double d = 6.0 * a * b;
        return b - (y * c - x * d) / (Math.pow(c, 2.0) + Math.pow(d, 2.0));
    }
}
