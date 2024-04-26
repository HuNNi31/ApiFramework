package loggerUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private static final Logger logger = LogManager.getLogger();

    public static void startTestCase(String testName){
        logger.info("****** Execution started: " + testName + " ******");
    }

    public static void finishTestCase(String testName){
        logger.info("****** Execution finished: " + testName + " ******");
    }

    public static void infoTest(String message){
        logger.info(message);
    }

    public static void errorTest(String message){
        logger.error(message);
    }

}
