import static java.lang.Double.NaN;

public class Triangle {
    public double countArea(int a, int b, int c) throws IllegalArgumentException {
        double area = 0;
        if (a > 0 && b > 0 && c > 0) {
            double p = (a + b + c) / 2.0;
            double tmpNumber = p * (p - a) * (p - b) * (p - c);
            if (tmpNumber >= 0) {
                area = Math.sqrt(tmpNumber);
            } else {
                throw new IllegalArgumentException("The triangle not exists");
            }
        } else {
            throw new IllegalArgumentException("Triangle contains negative number or null");
        }

        return area;
    }
}
