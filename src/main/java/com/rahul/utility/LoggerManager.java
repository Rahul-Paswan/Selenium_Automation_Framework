package com.rahul.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


public class LoggerManager {
    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<>();

    public static void createLogger(String testName) {
        ThreadContext.put("testName", testName); // This activates the route in log4j2.xml
        Logger logger = LogManager.getLogger(testName);
        threadLocalLogger.set(logger);
    }

    public static Logger getLogger() {
        return threadLocalLogger.get();
    }

    public static void removeLogger() {
        ThreadContext.remove("testName");
        threadLocalLogger.remove();
    }
}


