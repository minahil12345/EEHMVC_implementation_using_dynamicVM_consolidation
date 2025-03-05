package VM_and_Host_Management;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HostManagement {
    private List<Host> hostList;

    public HostManagement() {
        this.hostList = new ArrayList<>();
    }

    // Methods for host management

    public Host createHost(int hostId, double cpuCapacity, double memoryCapacity) {
        Host host = new Host(hostId, cpuCapacity, memoryCapacity);
        hostList.add(host);
        return host;
    }

    public void removeHost(Host host) {
        // Remove host and migrate its VMs to other hosts
        hostList.remove(host);
        redistributeVMs(host);
    }

    private void redistributeVMs(Host removedHost) {
        // Redistribute VMs from the removed host to other hosts
        for (VM vm : removedHost.getVmList()) {
            Host destinationHost = findDestinationHost(vm);
            removedHost.removeVM(vm);
            destinationHost.addVM(vm);
        }
    }

    public Host findDestinationHost(VM vm) {
        // Find a suitable host for migrating the VM
        // Implement your logic here, e.g., selecting a host with the lowest utilization
        Host host1 = hostList.stream()
                .min(Comparator.comparingDouble(host -> host.getCpuUtilization() + host.getMemoryUtilization()))
                .orElse(null);
        return host1;
    }

    public List<Host> getHostList() {
        return hostList;
    }
}
