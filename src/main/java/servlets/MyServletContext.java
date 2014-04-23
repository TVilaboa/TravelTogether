package servlets;


import org.hsqldb.Server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 02/04/2014
 * Time: 08:07 PM
 */
public class MyServletContext implements ServletContextListener {

    ServletContext context;
    Server hsqlServer = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {



        hsqlServer = new Server();

        // HSQLDB prints out a lot of informations when
        // starting and closing, which we don't need now.
        // Normally you should point the setLogWriter
        // to some Writer object that could store the logs.
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);

        // The actual database will be named 'xdb' and its
        // settings and data will be stored in files
        // testdb.properties and testdb.script
        hsqlServer.setDatabaseName(0, "TravelTogether");
        String s = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        s = "file:" + s.split("out")[0] + "DataBase/";
        s = s.replace('%', ' ');
        System.out.println(s);
        hsqlServer.setDatabasePath(0, s);
        System.out.println(hsqlServer.getDatabasePath(0, true));

        // Start the database!
        hsqlServer.start();

        System.out.println("Context Created");
        context = servletContextEvent.getServletContext();

        // set variable to servlet context

        context.setAttribute("TEST", "TEST_VALUE");


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        context = servletContextEvent.getServletContext();

        System.out.println("Context Destroyed");
        hsqlServer.shutdown();

    }
}
