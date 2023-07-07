/**
 * The Canvas object
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;

public class Graph extends Canvas {
    static final int LENGTH = 1100;
    static final int HEIGHT = 700;
    static final double ANGLE_CHANGE = 0.05;
    static final Matrix PROJECTION_MATRIX = new Matrix(new double[][] {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 0}});

    private double z_angle;
    private double y_angle;
    private double x_angle;
    private double scalar;
    Matrix rotation_z;
    Matrix rotation_y;
    Matrix rotation_x;

    public Graph() {
        z_angle = 0.0;
        y_angle = 0.0;
        x_angle = 0.0;
        scalar = 1;
        rotation_z = new Matrix(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}});
        rotation_y = new Matrix(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}});
        rotation_x = new Matrix(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}});
    }

    public void paint(Graphics g) {
        // Generates a 3D plane represented by an ArrayList of matrices
        Object object = new Object();
        // Sets the z value of each matrix to the number of
        // iterations until a root was approximated
        new ApplyMethod(object.getObject(), 2, 0, 0);

        for (int i = 0; i < object.getObject().size(); i++) {
            // Multiplies rotation and projection matrices
            Matrix rotated2D = rotation_z.multiply(object.getObject().get(i));
            rotated2D = rotation_y.multiply(rotated2D);
            rotated2D = rotation_x.multiply(rotated2D);
            Matrix scalar2D = rotated2D.multiply_scalar(scalar);
            Matrix projected2D = PROJECTION_MATRIX.multiply(scalar2D);

            // Converts the point to the Java coordinate system
            formatPoint(projected2D);
            int x = (int) projected2D.getMatrix()[0][0];
            int y = (int) projected2D.getMatrix()[1][0];

            // Sets shading
            double z = object.getObject().get(i).getMatrix()[2][0] / 20.0;
            int color;
            if (z > 25) {
                color = 0;
            } else {
                color = (int) (255.0 - (z * 10));
            }
            g.setColor(new Color(70, 0, color));

            // displays the point
            g.fillRect(x, y, 1, 1);
        }
    }

    public void stretch() {
       scalar += 0.1;
       repaint();
    }

    public void shrink() {
        scalar -= 0.1;
        repaint();
    }

    public void rotate_z(boolean positive) {
        if (positive)
            z_angle += ANGLE_CHANGE;
        else
            z_angle -= ANGLE_CHANGE;
        rotation_z = new Matrix(new double[][] {
                {Math.cos(z_angle), -Math.sin(z_angle), 0},
                {Math.sin(z_angle), Math.cos(z_angle), 0},
                {0, 0, 1}});
        repaint();
    }

    public void rotate_y(boolean positive) {
        if (positive)
            y_angle += ANGLE_CHANGE;
        else
            y_angle -= ANGLE_CHANGE;
        rotation_y = new Matrix(new double[][] {
                {Math.cos(y_angle), 0, Math.sin(y_angle)},
                {0, 1, 0},
                {-Math.sin(y_angle), 0, Math.cos(y_angle)}});
        repaint();

    }

    public void rotate_x(boolean positive) {
        if (positive)
            x_angle += ANGLE_CHANGE;
        else
            x_angle -= ANGLE_CHANGE;
        rotation_x = new Matrix(new double[][] {
                {1, 0, 0},
                {0, Math.cos(x_angle), -Math.sin(x_angle)},
                {0, Math.sin(x_angle), Math.cos(x_angle)}});
        repaint();
    }

    // Formats the given point from the cartesian plane
    // to the Java coordinate system
    private static void formatPoint(Matrix matrix) {
        double new_x = matrix.getMatrix()[0][0] + LENGTH / 2.0;
        double new_y = -1 * (matrix.getMatrix()[1][0] - HEIGHT / 2.0);
        matrix.setMatrix(0, 0, new_x);
        matrix.setMatrix(1, 0, new_y);
    }
}
