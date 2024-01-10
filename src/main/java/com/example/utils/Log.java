package com.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log =  LogManager.getLogger(Log.class);
    //Info Level Logs
    public static void infoYellow (String message) {
        Log.info(AnsiColorUtils.applyYellow(message));
    }

    public static void infoGreen (String message) {
        Log.info(AnsiColorUtils.applyGreen(message));
    }

    public static void infoPurple (String message) {
        Log.info(AnsiColorUtils.applyPurple(message));
    }
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(AnsiColorUtils.applyYellow(message));
    }
    //Error Level Logs
    public static void error (String message) {
        Log.error(AnsiColorUtils.applyRed(message));
    }
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(AnsiColorUtils.applyRed(message));
    }
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}