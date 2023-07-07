/**
 * Stores a Graph.Length x Graph.Height plane as an arraylist of matrices
 * Each matrix is a matrix 3x1 with the following values
 * [real part,
 * complex part,
 * number of iterations until a root was approximated]
 */
import java.util.ArrayList;

public class Object {
    public ArrayList<Matrix> object = new ArrayList<Matrix>();

    public Object() {
        for (int row = -Graph.LENGTH / 2; row <= Graph.LENGTH / 2; row++) {
            for (int col = -Graph.HEIGHT / 2; col <= Graph.HEIGHT / 2; col++) {
                object.add(new Matrix(new double[][] {{row}, {col}, {0}}));
            }
        }
    }

    public ArrayList<Matrix> getObject() {
        return this.object;
    }
}
