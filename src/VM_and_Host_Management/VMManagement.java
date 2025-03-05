package VM_and_Host_Management;

import java.util.ArrayList;
import java.util.List;

public class VMManagement {
    private List<VM> vmList;

    public VMManagement() {
        this.vmList = new ArrayList<>();
    }

    // Methods for VM management

    public VM createVM(int vmId, double cpuUtilization, double memoryUtilization) {
        VM vm = new VM(vmId, cpuUtilization, memoryUtilization);
        vmList.add(vm);
        return vm;
    }

    public void migrateVM(VM vm, Host sourceHost, Host destinationHost) {
        // Migrate VM from source host to destination host
        sourceHost.removeVM(vm);
        destinationHost.addVM(vm);
    }

    public void deleteVM(VM vm, Host host) {
        // Delete VM from the host
        host.removeVM(vm);
    }
}
