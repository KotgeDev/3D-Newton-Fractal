public class Complex {
    private double a;
    private double b;
    private int count;

    public Complex() {
        this.a = 0.0;
        this.b = 0.0;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public int getCount() {
        return this.count;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return String.format("%d, %d", this.a, this.b);
    }
}
