package VM_and_Host_Management;

public class VM {
    private int vmId;
    private double cpuUtilization;
    private double memoryUtilization;
    private Host currentHost;  // New attribute to store the current host information


    public VM(int vmId, double cpuUtilization, double memoryUtilization) {
        this.vmId = vmId;
        this.cpuUtilization = cpuUtilization;
        this.memoryUtilization = memoryUtilization;
    }

    // Getter and Setter methods

    public int getVmId() {
        return vmId;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public double getMemoryUtilization() {
        return memoryUtilization;
    }

    public void setMemoryUtilization(double memoryUtilization) {
        this.memoryUtilization = memoryUtilization;
    }

    public boolean isCPUIntensive(double cpuThreshold) {
        return this.getCpuUtilization() >= cpuThreshold;
    }
    public void setCurrentHost(Host currentHost) {
        this.currentHost = currentHost;
    }

    public Host getCurrentHost() {
        return currentHost;
    }
}
