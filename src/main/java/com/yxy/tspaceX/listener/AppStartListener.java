package com.yxy.tspaceX.listener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yxy.tspaceX.util.roomupdate;

/**
 * Application Lifecycle Listener implementation class TimerTaskListener
 *
 */
@WebListener
public class AppStartListener implements ServletContextListener {
	
	private roomupdate roomupdate;

    /**
     * Default constructor. 
     */
    public AppStartListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	roomupdate.taskstop();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	roomupdate = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(roomupdate.class);
    	roomupdate.taskbegin();
    	arg0.getServletContext().setAttribute("nulllist", new ArrayList<HashMap<String, Object>>());
    }
	
}
