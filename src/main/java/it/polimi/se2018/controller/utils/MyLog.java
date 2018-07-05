package it.polimi.se2018.controller.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLog {
    private static MyLog mylog;
    private Logger log;
    FileHandler fh;




    private MyLog(){
        log=Logger.getLogger(MyLog.class.getName());
        {
            try {
                fh = new FileHandler("/log");
                log.addHandler(fh);
                fh.setFormatter(new SimpleFormatter());
            } catch (IOException e) {
            }
        }
    }

    public static MyLog getMyLog(){
        if (mylog==null){
            mylog=new MyLog();
        }
        return mylog;
    }
    public void log(Level level,String name){
        log.log(level,name);
    }
}
