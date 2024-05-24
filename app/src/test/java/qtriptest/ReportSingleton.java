package qtriptest;

import java.io.File;
import java.sql.Timestamp;
// import java.security.Timestamp;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {
    private static ReportSingleton instanceOfSingletonReport=null;
    private ExtentReports report;

    public static String getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();

    }

    private ReportSingleton(){
        report=new ExtentReports(System.getProperty("user.dir") + "/ExtenReportResults" +getTimeStamp()+".html");
        report.loadConfig(new File("extent_custamiztaions_configs.xml"));
    }

    public static ReportSingleton getInstanceOfSingletonReportClass(){
        if(instanceOfSingletonReport==null){
            instanceOfSingletonReport=new ReportSingleton();
        }
        return instanceOfSingletonReport;
    }

    public ExtentReports getReport(){
        return report;
    }
}
