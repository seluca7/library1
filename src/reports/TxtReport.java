package reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtReport extends Report {
    @Override
    public void generateReport(String reportContent) {
        try (FileWriter writer = new FileWriter("report.txt");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(reportContent);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
