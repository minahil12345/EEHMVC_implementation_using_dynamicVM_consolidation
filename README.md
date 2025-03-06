# EEHMVC_implementation_using_dynamicVM-_consolidation
Implemented a research paper using the CloudSim simulator in Java, using a structured approach named EEHMVC to utilize power consumption and SLA violations using dynamic VM consolidation in cloud data centers

## **Overview**
This repository contains the implementation of the research paper:
> **"Utilizing Power Consumption and SLA Violations using Dynamic VM Consolidation in Cloud Data Centers"**  
> Published in *Renewable and Sustainable Energy Reviews, Volume 167 (2022), Article 112782*  
> Authors: Umer Arshad, Muhammad Aleem, Gautam Srivastava, Jerry Chun-Wei Lin  

This project focuses on reducing power consumption in cloud data centers while ensuring minimal Service Level Agreement (SLA) violations using an **Energy-Efficient Heuristic Virtual Machine Consolidation (EEHVMC)** approach.

## **Features**
- Implements **Dynamic VM Consolidation (DVMC)** for energy efficiency.
- Uses **CloudSim** simulator for modeling and analysis.
- Reduces **power consumption** and **SLA violations** through optimized VM placement.
- Supports **adaptive thresholding** for host classification (Overloaded, Medium-Loaded, Underloaded).
- Implements **heuristic scheduling** to minimize VM migrations.

## **Technologies Used**
- **Java JDK**: 21.0.2  
- **CloudSim**: Version 3.0.2  
- **Dataset**: PlanetLab, Google Cloud Jobs (GoCJ)  
- **IDE**: Eclipse/IntelliJ IDEA (recommended)

## **Installation & Setup**
### **Prerequisites**
Ensure you have the following installed:
- Java Development Kit (JDK) **21.0.1**
- **CloudSim** Simulator (download from [CloudSim GitHub](https://github.com/Cloudslab/cloudsim))
- A Java IDE (Eclipse, IntelliJ IDEA, or NetBeans)

### **Clone the Repository**
```bash
git clone https://github.com/minahil12345/EEHMVC_implementation_using_dynamicVM_consolidation.git
cd EEHMVC_implementation_using_dynamicVM_consolidation
```

### **Project Configuration**
1. **Set up CloudSim** in your project:
   - Download CloudSim from the [official repository](https://github.com/Cloudslab/cloudsim).
   - Import the `cloudsim-3.0.2.jar` into your Java project.

2. **Compile and Run**  
   - Open the project in your preferred IDE.
   - Ensure `CloudSim` dependencies are correctly configured.
   - Run the main class `MainSimulation.java` to execute the simulation.

## **Usage**
### **Running the Simulation**
1. Open the project in **Eclipse/IntelliJ**.
2. Ensure `CloudSim` library is correctly linked.
3. Run the `MainSimulation.java` file to start the simulation.
4. Results will be displayed in the console and logged in a results file.

### **Expected Output**
- **Energy Consumption (kWh)**
- **VM Migrations**
- **Performance Degradation due to Migration**
- **SLA Violations**
- **Execution Time**

## **Results & Performance**
The proposed **EEHVMC** approach was evaluated using **PlanetLab** and **GoCJ datasets** and compared with baseline VM consolidation techniques:
- **Reduced power consumption** by **16.45%** compared to existing methods.
- **Fewer VM migrations**, improving system stability.
- **Lower SLA violations**, ensuring better Quality of Service (QoS).

## **File Structure**
```
cloudsim-3.0.3/
│── docs/
│── examples/
│── jars/
│── sources/
│── build.xml
│── changelog.txt
│── examples.txt
│── license.txt
│── pom.xml
│── readme.txt
│── release_notes.txt
│
├── Results/
│   ├── results.txt
│
├── src/
│   ├── .git/
│   ├── EEHVMC_algorithm/
│   ├── Results/
│   ├── Threshold_Calculation/
│   ├── VM_and_Host_Management/
│   ├── VM_Selection_and_Placement/
│   ├── Main.java
```


## **Citation**
If you use this code, please cite the research paper:
```
@article{arshad2022vmconsolidation,
  title={Utilizing Power Consumption and SLA Violations using Dynamic VM Consolidation in Cloud Data Centers},
  author={Arshad, Umer and Aleem, Muhammad and Srivastava, Gautam and Lin, Jerry Chun-Wei},
  journal={Renewable and Sustainable Energy Reviews},
  volume={167},
  year={2022},
  doi={10.1016/j.rser.2022.112782}
}
```

## **License**
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
