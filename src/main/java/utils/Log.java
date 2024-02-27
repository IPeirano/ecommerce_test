package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
    private static Logger logger = Logger.getLogger(Log.class.getName());

    public static Logger getLogData(String className) {
        String separator = System.getProperty("file.separator");
        String path = "src" + separator + "main" + separator + "resources" + separator + "log4j.xml";
        DOMConfigurator.configure(path);
        return Logger.getLogger(className);
    }
    public static void startTest(String testName) {
        logger.info("Executing test: " + testName);
    }

    public static void endTest(String testName) {
        logger.info("The test " + testName + " finished successfully.");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}