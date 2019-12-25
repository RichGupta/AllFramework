package Utility.ExtentReport;

import Utility.ILogger;
import Utility.ListenerClass;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;

public class ExtentLogger implements ILogger {

    private static ExtentLogger Log = null;
    static ListenerClass listenerClass = new ListenerClass();
    private final static AtomicInteger jsonCount = new AtomicInteger(0);

    public static ExtentLogger getLogger() {
        if (Log == null)
            Log = new ExtentLogger();
        return Log;
    }

    public void info(String text){
        log.info(text);
        if(ListenerClass.getInstance() != null)
            ListenerClass.getInstance().log(Status.INFO,text);
    }

    public void error(String text){
        log.error(text);
        if(ListenerClass.getInstance() != null)
            ListenerClass.getInstance().log(Status.ERROR,text);
    }

    public void warn(String text){
        log.warn(text);
        if(ListenerClass.getInstance() != null)
            ListenerClass.getInstance().log(Status.WARNING,text);
    }

    public void fatal(String text){
        log.fatal(text);
        if(ListenerClass.getInstance() != null)
            ListenerClass.getInstance().log(Status.FATAL,text);
    }

    public void jsonInfo(String heading, String body){
        int json = jsonCount.incrementAndGet();
        try {
            ObjectMapper mapper = new ObjectMapper();
            if(listenerClass.isJSONValid(body)){
                log.info(body);
                Object jsonObject = mapper.readValue(body, Object.class);
                if(ListenerClass.getInstance() != null)
                    ListenerClass.getInstance().log(Status.INFO, MarkupHelper.createLabel("<a href='#"+json+"' data-featherlight='#"+json+"' style='color:white;'> Click here for : "+ heading +" <div style='display:none;'><div id='"+json+"'><pre>" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject) + "</pre></div></div></a>", ExtentColor.BLUE));
            }
        } catch (Exception e){
            log.info(body);
        }
    }

    public void screenshot(String title, WebDriver driver){
        log.info("Adding screenShot for :: "+title);
        if(ListenerClass.getInstance() != null){
            ListenerClass.getInstance().log(Status.INFO,title);
            listenerClass.takeScreenShot(driver);
        }
    }
}