package Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ILogger {

    public static Logger log = LogManager.getLogger(ILogger.class);
}