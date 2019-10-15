package Utility;

import org.apache.logging.log4j.LogManager;

public class Logger {

    public static org.apache.logging.log4j.Logger log = LogManager.getLogger(Logger.class);

    public static void main(String[] args){
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
        log.fatal("This is a fatal message");
    }
}
