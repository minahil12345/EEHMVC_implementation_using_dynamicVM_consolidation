package VM_Selection_and_Placement;
import VM_Selection_and_Placement.SLAViolations;

public class PerformanceMetrics {
    private int M; // Number of VMs
    private double[] Cd; // Array of estimated performance degradation of VM j due to migrations (PDM)
    private double[] Cr; // Array of total CPU capacity requested by VM j during its lifetime
    private SLAViolations slaViolations; // Instance of SLAViolations

    public PerformanceMetrics(int M, double[] Cd, double[] Cr, SLAViolations slaViolations) {
        this.M = M;
        this.Cd = Cd;
        this.Cr = Cr;
        this.slaViolations = slaViolations;
    }

    public double calculatePDM() {
        double sum = 0;
        for (int j = 0; j < M; j++) {
            sum += 0.1 * Cr[j]; // PDM is 10% of the CPU workload
        }
        return (1.0 / M) * sum;
    }

    public double calculateSLAV() {
        double SLATAH = slaViolations.calculateSLATAH();
        return SLATAH * calculatePDM();
    }
}