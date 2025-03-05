package VM_Selection_and_Placement;

import EEHVMC_algorithm.MigrationMap;
import VM_and_Host_Management.Host;
import VM_and_Host_Management.HostManagement;
import VM_and_Host_Management.VM;
import VM_and_Host_Management.VMManagement;
import VM_Selection_and_Placement.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VMPlacementAndSelection {

    private final StringBuilder resultsBuilder;
    private VMManagement vmManagement;
    private HostManagement hostManagement;

    private PowerModel powerModel;

    private static final double highThreshold = 80.0;
    private static final double lowThreshold = 20.0;

    // Power Model parameters
    private double Ps; // Fixed power consumption
    private double Pcpu; // CPU power consumption
    private double Pmemory; // Memory power consumption
    private double K; // Percentage of power consumed by idle hosts

    // SLA Violations parameters
    private int N; // Number of hosts
    private double[] Ts; // Array of total time host i experienced full utilization
    private double[] Ta; // Array of total time host i served in the active state

    // Performance Metrics parameters
    private int M; // Number of VMs
    private double[] Cd; // Array of estimated performance degradation of VM j due to migrations (PDM)
    private double[] Cr; // Array of total CPU capacity requested by VM j during its lifetime

    public VMPlacementAndSelection(VMManagement vmManagement, HostManagement hostManagement,
                                   double Ps, double Pcpu, double Pmemory, double K,
                                   int N, double[] Ts, double[] Ta,
                                   int M, double[] Cd, double[] Cr) {
        this.vmManagement = vmManagement;
        this.hostManagement = hostManagement;
        this.powerModel = new PowerModel(Ps, Pcpu, Pmemory, K);
        this.Ps = Ps;
        this.Pcpu = Pcpu;
        this.Pmemory = Pmemory;
        this.K = K;
        this.N = N;
        this.Ts = Ts;
        this.Ta = Ta;
        this.M = M;
        this.Cd = Cd;
        this.Cr = Cr;
        this.resultsBuilder = new StringBuilder();
    }

    public void applyVMPlacementAndSelection() {
        List<Host> hostList = hostManagement.getHostList();
        List<VM> vmList = vmManagement.getVMList();

        for (Host host : hostList) {
            double cpuUsage = host.getCpuUtilization();
            double memoryUsage = host.getMemoryUtilization();
            // Calculate total power consumption
            double totalPower = powerModel.calculateTotalPower(cpuUsage, memoryUsage);
//            if (cpuUsage >= highThreshold || memoryUsage >= highThreshold) {
//                // Host Over-Loaded (HOL)
//                migrateVMsFromHOL(host, vmList, highThreshold, lowThreshold);
//            } else if (highThreshold <= cpuUsage && cpuUsage <= lowThreshold
//                    && highThreshold <= memoryUsage && memoryUsage <= lowThreshold) {
//                // Host Medium-Loaded (HML)
//                // No migration needed, leave VMs unchanged
//            } else {
//                // Host Under-Loaded (HUL)
//                migrateVMsFromHUL(host, vmList, highThreshold, lowThreshold);
//            }

            // Evaluate host metrics
            evaluateHost(host);
        }

        // 4.8 Virtual Machine Selection and Placement
        List<VM> selectedVMs = selectVMsForPlacement(vmList);

        // 4.9 EEHVMC Algorithm
        MigrationMap migrationMap = applyEEHVMCAlgorithm(hostList, selectedVMs, highThreshold, lowThreshold);
    }

    private void migrateVMsFromHOL(Host host, List<VM> vmList, double highThreshold, double lowThreshold) {
        // Select VMs for migration using the MRCU policy
        //List<VM> selectedVMs = selectVMsForPlacement(vmList);

        for (VM vm : vmList) {
            double vmCpuUsage = vm.getCpuUtilization();
            double vmMemoryUsage = vm.getMemoryUtilization();

            if (vmCpuUsage >= highThreshold || vmMemoryUsage >= highThreshold) {
                // Migrate VM to another host
                Host destinationHost = findTargetHost(vm);
                if (destinationHost != host) {
                    vmManagement.migrateVM(vm, host, destinationHost);
                }
            }
        }
    }

    private void migrateVMsFromHUL(Host host, List<VM> vmList, double highThreshold, double lowThreshold) {
        for (VM vm : vmList) {
            Host destinationHost = findTargetHost(vm);
            if (destinationHost != host) {
                vmManagement.migrateVM(vm, host, destinationHost);
            }
        }
        host.setSleepMode(true); // Put host into sleep mode
//        System.out.println("Host " + host.getHostId() + " is now in sleep mode.");
    }

    private Host findTargetHost(VM vm) {
        return hostManagement.findDestinationHost(vm);
    }

    private void evaluateHost(Host host) {
        PowerModel powerModel = new PowerModel(Ps, Pcpu, Pmemory, K);
        double cpuUsage = host.getCpuUtilization();
        double memoryUsage = host.getMemoryUtilization();
        // Calculate total power consumption
        double totalPower = powerModel.calculateTotalPower(cpuUsage, memoryUsage);

        SLAViolations slaViolations = new SLAViolations(N, Ts, Ta);
        double SLATAH = slaViolations.calculateSLATAH();

        PerformanceMetrics performanceMetrics = new PerformanceMetrics(M, Cd, Cr, slaViolations);
        double SLAV = performanceMetrics.calculateSLAV();

        // Combine metrics or perform any other actions based on your requirements
        double ESV = totalPower * SLAV;

        System.out.println("Host ID: " + host.getHostId());
        System.out.println("Total Power: " + totalPower);
        System.out.println("SLATAH: " + SLATAH);
        System.out.println("SLAV: " + SLAV);
        System.out.println("ESV: " + ESV + "\n");

        // Append metrics to the resultsBuilder
        resultsBuilder.append("Host ID: ").append(host.getHostId()).append("\n");
        resultsBuilder.append("Total Power: ").append(totalPower).append("\n");
        resultsBuilder.append("SLATAH: ").append(SLATAH).append("\n");
        resultsBuilder.append("SLAV: ").append(SLAV).append("\n");
        resultsBuilder.append("ESV: ").append(ESV).append("\n");
        resultsBuilder.append("\n");
    }

    /**
     * Select VMs for migration using the MRCU policy.
     * VMs are sorted in descending order of their CPU-to-memory utilization ratio.
     */
    private List<VM> selectVMsForPlacement(List<VM> vmList) {
        return vmList.stream()
                .sorted(Comparator.comparingDouble((VM vm) -> vm.getCpuUtilization() / vm.getMemoryUtilization()).reversed())
                .collect(Collectors.toList());
    }

    private MigrationMap applyEEHVMCAlgorithm(List<Host> hostList, List<VM> selectedVMs, double highThreshold, double lowThreshold) {
        MigrationMap migrationMap = new MigrationMap();

        for (Host host : hostList) {
            double cpuUsage = host.getCpuUtilization();
            double memoryUsage = host.getMemoryUtilization();

            if (cpuUsage >= highThreshold || memoryUsage >= highThreshold) {
                // Host Over-Loaded (HOL)
               // migrateVMsFromHOL(host,selectedVMs,highThreshold,lowThreshold);
                for (VM vm : selectedVMs) {
                    if (vm.getCurrentHost() == host) {
                        Host targetHost = findTargetHostForVM(vm, hostList, highThreshold, lowThreshold);
                        if (targetHost != null && targetHost != host) {
                            migrationMap.addMigration(vm, host, targetHost);
                        }
                    }
                }
            } else if (cpuUsage < lowThreshold && memoryUsage < lowThreshold) {
                // Host Under-Loaded (HUL)
               // migrateVMsFromHUL(host,selectedVMs,highThreshold,lowThreshold);
                for (VM vm : selectedVMs) {
                    if (vm.getCurrentHost() == host) {
                        Host targetHost = findTargetHostForVM(vm, hostList, highThreshold, lowThreshold);
                        if (targetHost != null && targetHost != host) {
                            migrationMap.addMigration(vm, host, targetHost);
                        }
                    }
                }
                host.setSleepMode(true); // Put host into sleep mode
                System.out.println("Host " + host.getHostId() + " is now in sleep mode.");
            }
            // Host Medium-Loaded (HML) - No action needed
        }

        return migrationMap;
    }

    private Host findTargetHostForVM(VM vm, List<Host> hostList, double highThreshold, double lowThreshold) {
        // Implement your logic to find the target host for VM migration
        // Consider factors like load balancing, resource availability, etc.
        return hostList.stream()
                .min(Comparator.comparingDouble(host -> host.getCpuUtilization() + host.getMemoryUtilization()))
                .orElse(null);
    }

    public String getResults() {
        return resultsBuilder.toString();
    }
}