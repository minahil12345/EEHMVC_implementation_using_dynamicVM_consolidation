package Threshold_Calculation;
import java.util.List;

public class AdaptiveThreshold {
    public double calculateIqr(List<Double> data) {
        int n = data.size();
        double q1 = data.get(n / 4);
        double q3 = data.get((3 * n) / 4);
        return q3 - q1;
    }

    public double calculateAdaptiveThreshold(List<Double> data, double iqr) {
        double median = data.get(data.size() / 2);
        return median - (1.5 * iqr);
    }
}
