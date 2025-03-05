import VM_Selection_and_Placement.VMPlacementAndSelection;
import VM_and_Host_Management.*;
import Threshold_Calculation.*;
import Results.*;
import EEHVMC_algorithm.*;

public class Main {

    public static void main(String[] args) {
        // Create instances of VMManagement and HostManagement
        VMManagement vmManagement = new VMManagement();
        HostManagement hostManagement = new HostManagement();

        // Specify parameters for VMPlacementAndSelection
        double Ps = 10.0; // Fixed power consumption
        double Pcpu = 5.0; // CPU power consumption
        double Pmemory = 2.0; // Memory power consumption
        double K = 0.2; // Percentage of power consumed by idle hosts

        int N = 5; // Number of hosts
        double[] Ts = {10.0, 15.0, 20.0, 25.0, 30.0}; // Total time host i experienced full utilization
        double[] Ta = {100.0, 100.0, 100.0, 100.0, 100.0}; // Total time host i served in the active state

        int M = 10; // Number of VMs
        double[] Cd = {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5}; // Estimated performance degradation of VM j due to migrations (PDM)
        double[] Cr = {10.0, 15.0, 20.0, 25.0, 30.0, 35.0, 40.0, 45.0, 50.0, 55.0}; // Total CPU capacity requested by VM j during its lifetime

        // Create VMPlacementAndSelection instance
        VMPlacementAndSelection placementAndSelection = new VMPlacementAndSelection(
                vmManagement, hostManagement, Ps, Pcpu, Pmemory, K, N, Ts, Ta, M, Cd, Cr
        );

        // Create VMs and hosts (You can customize these values)
        VM vm1 = vmManagement.createVM(1, 30.0, 40.0); // VM ID, CPU Utilization, Memory Utilization
        VM vm2 = vmManagement.createVM(2, 70.0, 20.0);
        VM vm3 = vmManagement.createVM(3, 50.0, 60.0);
        VM vm4 = vmManagement.createVM(4, 80.0, 30.0);
        VM vm5 = vmManagement.createVM(5, 40.0, 50.0);

        Host host1 = hostManagement.createHost(1, 100.0, 100.0); // Host ID, CPU Capacity, Memory Capacity
        Host host2 = hostManagement.createHost(2, 80.0, 120.0);
        Host host3 = hostManagement.createHost(3, 120.0, 80.0);
        Host host4 = hostManagement.createHost(4, 90.0, 90.0);
        Host host5 = hostManagement.createHost(5, 110.0, 110.0);

        // Assign VMs to hosts (You can customize this logic)
        host1.addVM(vm1);
        host2.addVM(vm2);
        host3.addVM(vm3);
        host4.addVM(vm4);
        host5.addVM(vm5);

        // Apply VM placement and selection
        placementAndSelection.applyVMPlacementAndSelection();

        // Create an instance of ResultsWriter to handle writing results to a file
        ResultsWriter resultsWriter = new ResultsWriter();

        // Get the results from VMPlacementAndSelection
        String results = placementAndSelection.getResults();

        // Write the results to a file
        resultsWriter.writeResultsToFile(results, "Results/results.txt");

        // Print final state or any other necessary information
        printSystemState(vmManagement, hostManagement);
    }

    private static void printSystemState(VMManagement vmManagement, HostManagement hostManagement) {
        System.out.println("VMs:");
        for (VM vm : vmManagement.getVMList()) {
            System.out.println("VM ID: " + vm.getVmId() +
                    ", CPU Utilization: " + vm.getCpuUtilization() +
                    ", Memory Utilization: " + vm.getMemoryUtilization());
        }

        System.out.println("\nHosts:");
        for (Host host : hostManagement.getHostList()) {
            System.out.println("Host ID: " + host.getHostId() +
                    ", CPU Utilization: " + host.getCpuUtilization() +
                    ", Memory Utilization: " + host.getMemoryUtilization());
        }
    }
}