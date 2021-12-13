package reports;

public class GetReportFactory {
    public Report getReport(FileType fileType){
        if(fileType == null){
            System.out.println("Format specificat incorect");
            return null;
        }
        else {
            if (fileType.equals(FileType.TXT)){
                return new TxtReport();
            }
            if (fileType.equals(FileType.CSV)){
                return new CsvReport();
            }
        }
        return null;
    }
}
