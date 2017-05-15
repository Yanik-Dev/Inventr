/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * wrapper for Logger class
 * @author Yanik
 */
public class AppLogger {
    private static Logger logger = null;
    private static Handler fileHandler = null;
    private static Handler consoleHandler = null;
    
    /**
     * returns instance of Logger
     * @param className
     * @return Logger
     */
    public static Logger getLogger(String className){
        logger = Logger.getLogger(className);
        try{
            //instantiate handlers
            fileHandler = new FileHandler("./logFile.log");
            consoleHandler = new ConsoleHandler();

            //add handlers to logger
            logger.addHandler(fileHandler);
            logger.addHandler(fileHandler);

        }catch(IOException | SecurityException e){
            Logger.getLogger(AppLogger.class.getName()).log(Level.SEVERE, "Exception occur", e);
        }
        
        return logger;
    } 
}
