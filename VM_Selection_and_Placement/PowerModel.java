package VM_Selection_and_Placement;

public class PowerModel {
    private double Ps; // Fixed power consumption
    private double Pcpu; // CPU power consumption
    private double Pmemory; // Memory power consumption
    private double K; // Percentage of power consumed by idle hosts

    public PowerModel(double Ps, double Pcpu, double Pmemory, double K) {
        this.Ps = Ps;
        this.Pcpu = Pcpu;
        this.Pmemory = Pmemory;
        this.K = K;
    }

    public double calculateTotalPower(double cpuUtilization, double memoryUtilization) {
        // Calculate total power consumption based on the provided formulas
        double PmaxCpu = calculateMaxCpuPower();
        double PmaxMemory = calculateMaxMemoryPower();

        return (K * PmaxCpu + (1 - K) * PmaxCpu * cpuUtilization) +
                (K * PmaxMemory + (1 - K) * PmaxMemory * memoryUtilization);
    }

    private double calculateMaxCpuPower() {
        // Calculate maximum CPU power consumption
        return Pcpu;
    }

    private double calculateMaxMemoryPower() {
        // Calculate maximum memory power consumption
        return Pmemory;
    }

    public double calculateUtilization(double usedResource, double totalResource) {
        // Calculate utilization as a percentage of the total resource capacity
        if (totalResource == 0) {
            return 0.0; // Avoid division by zero
        }
        return usedResource / totalResource;
    }
}