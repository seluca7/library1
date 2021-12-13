package reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvReport extends Report {
    public void generateReport(String reportContent) {
        try (FileWriter writer = new FileWriter("report.csv");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(reportContent);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
