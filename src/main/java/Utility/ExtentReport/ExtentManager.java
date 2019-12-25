package Utility.ExtentReport;

import Utility.ILogger;
import Utility.PropertyReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtentManager implements ILogger {

    private static ExtentReports extentReports;
    private static String suitName;
    private static String reportPath = System.getProperty("user.dir")+"/Extent-Reports";
    private static Properties config = PropertyReader.loadCustomProperties("config.properties");
    private static Properties klovConfig = PropertyReader.loadCustomProperties("klovConfig.properties");
    private final static AtomicInteger extentCount = new AtomicInteger(0);

    public static ExtentReports getInstance(String suit){
        suitName = suit;
        if(extentReports==null)
            createInstance();
        return extentReports;
    }

    public static String getSuitName(){
        return suitName;
    }

    public static ExtentKlovReporter getExtentKlovReporterInstance(){
        ExtentKlovReporter klovReporter = new ExtentKlovReporter();
        if ((!(klovConfig.getProperty("extentKlov.host").isEmpty())) && (!(klovConfig.getProperty("extentKlov.port").isEmpty()))){
            try{
                klovReporter.initMongoDbConnection(klovConfig.getProperty("extentKlov.host"),27017);
                klovReporter.setReportName(getSuitName()+"_"+extentCount);
                klovReporter.setProjectName(getSuitName());
                klovReporter.initKlovServerConnection(klovConfig.getProperty("extentKlov.protocol") + "://"+ klovConfig.getProperty("extentKlov.host") + ":"+ klovConfig.getProperty("extentKlov.port"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return klovReporter;
    }

    public static ExtentReports createInstance(){
        ExtentHtmlReporter extentHtmlReporter = null;
        if(deletePreviousFiles())
            extentHtmlReporter = new ExtentHtmlReporter(getReportPath(reportPath) + getSuitName() + "_" + java.time.LocalDateTime.now() + ".html");
        else
            extentHtmlReporter = new ExtentHtmlReporter(getReportPath(reportPath) + getSuitName() + "_" + ".html");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setReportName(getSuitName());
        extentHtmlReporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        try{
            if(config.getProperty("UPDATETOKLOV").equalsIgnoreCase("true")){
                    ExtentKlovReporter xReporter = getExtentKlovReporterInstance();
                    if (getExtentKlovReporterInstance() != null)
                        extentReports.attachReporter(extentHtmlReporter, xReporter);
            } else
                extentReports.attachReporter(extentHtmlReporter);
        } catch (Exception e){
            extentReports.attachReporter(extentHtmlReporter);
        }
        return extentReports;
    }

    public static String getReportPath(String reportPath){
        File directory = new File(reportPath);
        String path = null;
        if(!directory.exists()){
            if(directory.mkdir()){
                path = reportPath+"/";
                log.info("Directory is created");
            } else {
                log.info("Not able to create directory");
            }
        }else{
            log.info("Directory already exists");
            path = reportPath+"/";
        }
        return path;
    }

    public static boolean deletePreviousFiles(){
        try{
            if(config.getProperty("DELETEREPORTFLAG").equalsIgnoreCase("true")){
                log.info("******** Deleting previous reports as flag is: "+config.getProperty("DELETEREPORTFLAG")+" and day window is: "+config.getProperty("DELETEWINDOW"));
                File directory = new File(reportPath+"/");
                File[] listFiles = directory.listFiles();
                try{
                    int i = Integer.parseInt(config.getProperty("DELETEWINDOW"));
                    long purgeTime = System.currentTimeMillis() - (i * 24 * 60 * 60 * 1000);
                    for(File listFile : listFiles) {
                        if((listFile.lastModified()) < purgeTime) {
                            listFile.delete();
                        }
                    }
                } catch (NumberFormatException ex){
                    for(File listFile:listFiles){
                        listFile.delete();
                    }
                }
            }
            return true;
        }catch (NullPointerException ex){
            log.info("No reports will be deleted");
            return false;
        }
    }
}
