package com.starters;

import com.controllers.configs.MainController;
import com.dao.impl.JDBCDaoFactory;
import com.resources.BeansDispatcher;

import java.util.HashMap;

public class ControllerStarter {
    public static void main(String[] args) throws Exception {
        if(args.length>0)
            JDBCDaoFactory.instantiateConnection(args[0],args[1]);
        else
            JDBCDaoFactory.instantiateConnection("root", "1234");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("Shutting down db connection..");
                try{
                    JDBCDaoFactory.closeConnection();
                    System.out.println("Shutting down completed successfully.");
                }catch (Exception e){
                    System.out.println("Error while closing connection! Stack trace is below:");
                    e.printStackTrace();
                }
            }
        });
        BeansDispatcher.instantiateControllers();
        MainController.getMainController().renderTemplate("/index", new HashMap<>());

    }
}
