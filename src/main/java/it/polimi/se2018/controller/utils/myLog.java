package it.polimi.se2018.controller.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class myLog {
    private static myLog mylog;
    private Logger log;
    FileHandler fh;


    private myLog() throws IOException {
        log=Logger.getLogger(myLog.class.getName());
        fh= new FileHandler("/");
        log.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
    }

    public static myLog getMyLog() throws IOException {
        if (mylog==null){
            mylog=new myLog();
        }
        return mylog;
    }
    public void log(Level level,String name){
        log.log(level,name);
    }
}
