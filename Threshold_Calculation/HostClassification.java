package Threshold_Calculation;
import VM_and_Host_Management.*;

import java.util.List;

public class HostClassification {
    private double highThreshold;
    private double lowThreshold;

    public HostClassification(double highThreshold, double lowThreshold) {
        this.highThreshold = highThreshold;
        this.lowThreshold = lowThreshold;
    }

    public void classifyHosts(List<Host> hosts) {
        for (Host host : hosts) {
            double cpuUtilization = host.getCpuUtilization();
            double memoryUtilization = host.getMemoryUtilization();

            if (cpuUtilization >= highThreshold || memoryUtilization >= highThreshold) {
                host.setClassification("Host OverLoaded (HOL)");
            } else if (highThreshold >= cpuUtilization && cpuUtilization >= lowThreshold
                    || highThreshold >= memoryUtilization && memoryUtilization >= lowThreshold) {
                host.setClassification("Host Medium-Loaded (HML)");
            } else {
                host.setClassification("Host Under-Loaded (HUL)");
            }
        }
    }
}
