package Results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsWriter {
    public void writeResultsToFile(String results, String filePath) {
        // Create the Results directory if it doesn't exist
        File directory = new File("Results");
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory and any necessary parent directories
        }

        // Write the results to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(results);
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}