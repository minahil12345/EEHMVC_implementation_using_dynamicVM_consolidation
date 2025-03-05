package VM_Selection_and_Placement;

import VM_Selection_and_Placement.SLAViolations;
public class PerformanceMetrics {
    private int M; // Number of VMs
    private double[] Cd; // Array of estimated performance degradation of VM j due to migrations (PDM)
    private double[] Cr; // Array of total CPU capacity requested by VM j during its lifetime

    public PerformanceMetrics(int M, double[] Cd, double[] Cr) {
        this.M = M;
        this.Cd = Cd;
        this.Cr = Cr;
    }

    public double calculatePDM() {
        double sum = 0;
        for (int j = 0; j < M; j++) {
            sum += Cd[j] / Cr[j];
        }
        return (1.0 / M) * sum;
    }

    public double calculateSLAV() {
        SLAViolations SLAV = null;
        double SLATAH= SLAV.calculateSLATAH();
        return SLATAH * calculatePDM();
    }
}
