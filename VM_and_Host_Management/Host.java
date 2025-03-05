package VM_and_Host_Management;

import java.util.ArrayList;
import java.util.List;

public class Host {
    private int hostId;
    private double cpuCapacity;
    private double memoryCapacity;
    private List<VM> vmList;
    private String classification;
    private boolean sleepMode; // New attribute to track sleep mode

    public Host(int hostId, double cpuCapacity, double memoryCapacity) {
        this.hostId = hostId;
        this.cpuCapacity = cpuCapacity;
        this.memoryCapacity = memoryCapacity;
        this.vmList = new ArrayList<>();
        this.sleepMode = false; // Default to active mode
    }

    // Getter and Setter methods

    public int getHostId() {
        return hostId;
    }

    public double getCpuCapacity() {
        return cpuCapacity;
    }

    public double getMemoryCapacity() {
        return memoryCapacity;
    }

    public List<VM> getVmList() {
        return vmList;
    }

    public void addVM(VM vm) {
        vmList.add(vm);
        vm.setCurrentHost(this); // Set the current host of the VM
    }

    public void removeVM(VM vm) {
        vmList.remove(vm);
        vm.setCurrentHost(null); // Clear the current host of the VM
    }

    public double getCpuUtilization() {
        // Calculate total CPU usage by summing the CPU usage of all VMs
        double totalCpuUsage = vmList.stream().mapToDouble(VM::getCpuUtilization).sum();
        return totalCpuUsage / cpuCapacity; // Utilization as a percentage
    }

    public double getMemoryUtilization() {
        // Calculate total memory usage by summing the memory usage of all VMs
        double totalMemoryUsage = vmList.stream().mapToDouble(VM::getMemoryUtilization).sum();
        return totalMemoryUsage / memoryCapacity; // Utilization as a percentage
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    // Sleep mode methods
    public boolean isSleepMode() {
        return sleepMode;
    }

    public void setSleepMode(boolean sleepMode) {
        this.sleepMode = sleepMode;
    }
}