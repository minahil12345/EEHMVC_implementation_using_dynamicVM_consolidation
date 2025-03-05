package VM_Selection_and_Placement;
public class SLAViolations {
    private int N; // Number of hosts
    private double[] Ts; // Array of total time host i experienced full utilization
    private double[] Ta; // Array of total time host i served in the active state

    public SLAViolations(int N, double[] Ts, double[] Ta) {
        this.N = N;
        this.Ts = Ts;
        this.Ta = Ta;
    }

    public double calculateSLATAH() {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Ts[i] / Ta[i];
        }
        return (1.0 / N) * sum;
    }
}
