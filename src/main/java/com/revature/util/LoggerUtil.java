package com.revature.util;

import org.apache.log4j.Logger;

/**
 * A class setup for static importation into other classes to allow for easy logging without writing
 * Logger.getRootLogger().Log... every time.
 * 
 * @author ErikHaklar
 */
public class LoggerUtil {
  private static Logger log = Logger.getRootLogger();

  public static void fatal(String message) {
    log.fatal(message);
  }

  public static void error(String message) {
    log.error(message);
  }

  public static void warn(String message) {
    log.warn(message);
  }

  public static void debug(String message) {
    log.debug(message);
  }

  public static void info(String message) {
    log.info(message);
  }

  public static void trace(String message) {
    log.trace(message);
  }
}
