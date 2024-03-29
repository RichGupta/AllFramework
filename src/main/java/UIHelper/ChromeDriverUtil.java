package UIHelper;

import java.io.File;
import java.nio.file.*;
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
import CommandHelper.CommandLineExecutor;
import Utility.ILogger;
import Utility.PropertyReader;

/**
 * Provides chrome browser and driver details programmatically
 * @author rich-gupta
 */

public class ChromeDriverUtil implements ILogger {

    private static final String DEFAULT_CHROME_DRIVER_DIR_PATH = Paths.get("src",
            new String[] { "test", "resources", "chromeDriver" }).toString();
    private static Properties chromeDriverBrowserMapping;
    private static final String DRIVER_BROWSER_COMPATIBILITY_FILE = Paths.get("chromeDriver",
            new String[] { "chromeDriver_browser_compatibility.properties" }).toString();

    /**
     * Provides Chrome driver executable path programmatically
     * @return Path - chrome driver path
     */

    public static Path getChromeExecutablePath() {
        String osArch = System.getProperty("os.arch").toLowerCase();
        String osName = System.getProperty("os.name").toLowerCase();
        log.info(DEFAULT_CHROME_DRIVER_DIR_PATH);
        Path path = Paths.get(
                DEFAULT_CHROME_DRIVER_DIR_PATH,
                new String[] {
                        getSupportedChromeDriverVersion(),
                        String.format("%s_",
                                new Object[] { osName.contains("linux") ? "linux" : (osName.contains("win") ? "win"
                                        : "mac") }) });
        String arch = osArch.indexOf("64") != -1 ? "64" : "32";
        File file = null;
        if(osName.contains("win")){
            file = new File(path.toString() + arch + ".exe");
        } else {
            file = new File(path.toString() + arch);
        }
        if (osArch.contains("64") && !file.exists()) {
            arch = "32";
        }
        file = new File(path.toString() + arch + (osName.contains("win") ? ".exe" : ""));
        String chromeDriverPath = file.exists() ? file.getAbsolutePath() : null;
        return chromeDriverPath == null ? null : Paths.get(chromeDriverPath, new String[0]);
    }

    /**
     * Fetches supported chrome driver versions
     * @return String - Chrome driver version
     */

    public static String getSupportedChromeDriverVersion() {
        Map<String, List<String>> matrix = getChromeBrowserCompatibilityMatrix();
        String chromeBrowserVersion = getChromeBrowserVersion();
        boolean isVersionNotAvailable = chromeBrowserVersion == null || chromeBrowserVersion.trim().isEmpty();
        for (Entry<String, List<String>> entry : matrix.entrySet()) {
            if (isVersionNotAvailable) {
                return entry.getKey();
            } else if (entry.getValue() != null && entry.getValue().contains(chromeBrowserVersion)) {
                return entry.getKey();
            }
        }
        log.info("required Chrome driver version : " + chromeBrowserVersion);
        return null;
    }

    /**
     * Loads matrix of chrome and chrome driver
     * @return Map - map of chrome version with respect to chrome driver version
     */

    private static Map<String, List<String>> getChromeBrowserCompatibilityMatrix() {
        Map<String, List<String>> matrix = new TreeMap<>((e1, e2) -> e2.compareTo(e1));
        if (chromeDriverBrowserMapping == null || chromeDriverBrowserMapping.isEmpty()) {
            chromeDriverBrowserMapping = PropertyReader.loadCustomProperties(DRIVER_BROWSER_COMPATIBILITY_FILE);
        }
        chromeDriverBrowserMapping.forEach((k, v) -> {
            String[] arr = ((String) v).split(",");
            List<String> values = new ArrayList<>();
            for (String str : arr) {
                values.add(str.trim());
            }
            matrix.put(((String) k).trim(), values);
        });
        return matrix;
    }

    /**
     * Provides chrome version
     * @return String - Chrome browser version
     */

    public static String getChromeBrowserVersion() {
        String osName = System.getProperty("os.name").toLowerCase();
        String command = null;
        if (osName.contains("linux")) {
            command = "google-chrome --product-version";
        } else if (osName.contains("mac")) {
            command = "'/Applications/Google Chrome.app/Contents/MacOS/Google Chrome' --version";
        } else if (osName.contains("win")) {
            command = "wmic datafile where name=\"C:\\\\Program Files (x86)\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe\" get Version /value";
        }
        String response = CommandLineExecutor.exec(command).trim();
        if(response != null && !response.trim().isEmpty()){
            if(response.contains("=")){
                response = response.substring(response.indexOf("=")+1);
            } else if(response.contains("Chrome")) {
                response = response.substring(response.lastIndexOf(" "));
            }
            return response.substring(0,response.indexOf(".")).trim();
        }
        else {
            return null;
        }
    }
}

