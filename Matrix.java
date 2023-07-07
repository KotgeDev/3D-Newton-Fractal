/**
 * The matrix object
 * Allows for simple matrix calculations
 */
public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(int row, int col, double value) {
        this.matrix[row][col] = value;
    }

    public Matrix multiply_scalar(double s) {
        Matrix product = new Matrix(new double[matrix.length][matrix[0].length]);
        for (int row = 0; row < product.matrix.length; row++) {
            for (int col = 0; col < product.matrix[row].length; col++) {
                product.matrix[row][col] = matrix[row][col] * s;
            }
        }

        return product;
    }

    public Matrix multiply(Matrix other) {
        if (this.matrix[0].length != other.matrix.length) {
            System.out.println(this.matrix[0].length);
            System.out.println(other.matrix.length);
            throw new ArithmeticException("First matrix row length and " +
                    "second matrix col length does not match");
        }
        // The product matrix
        Matrix product = new Matrix(new double[this.matrix.length][other.matrix[0].length]);

        // Sets each value of the product matrix
        for (int row = 0; row < product.matrix.length; row++) {
            for (int col = 0; col < product.matrix[row].length; col++) {
                double sum = 0;
                for (int i = 0; i < this.matrix[0].length; i++) {
                    sum += this.matrix[row][i] * other.matrix[i][col];
                }
                product.matrix[row][col] = sum;
            }
        }

        return product;

    }

    public String toString() {
        String nice = "";

        for (int row = 0; row < this.matrix.length; row++) {
            nice = nice + "[";
            for (int col = 0; col < this.matrix[row].length; col++) {
                // If it is the last number in the row
                if (col == this.matrix[row].length - 1)
                    nice += this.matrix[row][col] + "]\n";
                else
                    nice += this.matrix[row][col] + ", ";
            }
        }

        return nice;
    }
}
