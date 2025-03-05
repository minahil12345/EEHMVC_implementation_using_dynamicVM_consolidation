package EEHVMC_algorithm;

import VM_and_Host_Management.Host;
import VM_and_Host_Management.VM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MigrationMap {

    private Map<VM, Host> migrationMap;

    public MigrationMap() {
        this.migrationMap = new HashMap<>();
    }

    public void addMigration(VM vm, Host sourceHost, Host destinationHost) {
        migrationMap.put(vm, destinationHost);
        System.out.println("VM " + vm.getVmId() + " migrated from Host " + sourceHost.getHostId() +
                " to Host " + destinationHost.getHostId());
    }

    public List<VM> getMigratedVMs() {
        return new ArrayList<>(migrationMap.keySet());
    }

    public Host getDestinationHost(VM vm) {
        return migrationMap.get(vm);
    }
}