package Utility;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyReader implements ILogger{

    /**
     * Reads properties file
     */
    public static void propertyReader() {
        String fileName = System.getProperty("user.dir")
                + "/src/test/resources/" + "config.properties";
        try {
            Properties prop = new Properties();
            InputStream is = new FileInputStream(fileName);
            prop.load(is);
            Enumeration<Object> keys = prop.keys();
            while (keys.hasMoreElements()) {
                String key = (String)keys.nextElement();
                String value = (String)prop.get(key);
                System.setProperty(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads custom property file
     */
    public static Properties loadCustomProperties(String fileName) {
        if (!fileName.contains(".properties")) {
            throw new AssertionError("Please specify a valid property file name with .properties extension");
        }
        URL file = PropertyReader.class.getClassLoader().getResource(fileName);
        if(file==null) {
            file = PropertyReader.class.getClassLoader().getResource(File.separator + fileName);
            if(file==null) {
                throw new AssertionError("could not load custom properties");
            }
        }
        log.debug("File to load :" + fileName);
        Properties prop = new Properties();
        InputStream istr = null;
        try {
            //istr = getFileInputStream(file);
            istr = file.openStream();
        } catch (FileNotFoundException e1) {
            log.error(e1.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        if (istr != null) {
            try {
                prop.load(istr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
