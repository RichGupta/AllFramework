package Utility;

import RestAPI.RestClient;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.ServerSocket;

public class AppiumHandler implements ILogger{

    private static AppiumDriverLocalService appiumDriverLocalService;
    private static AppiumServiceBuilder appiumServiceBuilder;
    private static DesiredCapabilities capabilities;

    public void startAppiumServer(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset","false");

        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumServiceBuilder.usingPort(4723);
        appiumServiceBuilder.withCapabilities(capabilities);
        appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();
    }

    public void stopAppiumServer() {
        appiumDriverLocalService.stop();
    }

    public boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public boolean checkIfServerIsRunning(String url){
        boolean isServerRunning = false;
        RestClient restTest = new RestClient();
        try {
            Response response = restTest.sendRequest(url, Method.GET,null);
            if(response.getStatusCode()==200)
                isServerRunning = true;
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return isServerRunning;
    }

    public static void main(String[] args) {
        AppiumHandler appiumServer = new AppiumHandler();

        int port = 4723;
        String url = "http://127.0.0.1:4723/wd/hub/status";
//        if(!appiumServer.checkIfServerIsRunning(port)) {
//            appiumServer.startAppiumServer();
//            if(appiumServer.checkIfServerIsRunning(port)){
//                System.out.println("Appium Server is running on Port - " + port);
//            }
//            appiumServer.stopAppiumServer();
//        } else {
//            System.out.println("Appium Server already running on Port - " + port);
//        }
        if(!appiumServer.checkIfServerIsRunning(url)) {
            appiumServer.startAppiumServer();
            if(appiumServer.checkIfServerIsRunning(url)){
                log.info("Appium Server is running on Port - " + port);
            }
            appiumServer.stopAppiumServer();
        } else {
            log.info("Appium Server already running on Port - " + port);
        }
    }
}
