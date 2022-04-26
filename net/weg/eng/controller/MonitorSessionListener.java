package net.weg.eng.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.weg.eng.service.MonitorManager;
import net.weg.eng.service.MonitorType;

public class MonitorSessionListener implements HttpSessionListener {
	
	private static final Logger LOGGER = LogManager.getLogger(MonitorSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
	}
 
	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		try { 
			String user = (String) sessionEvent.getSession().getAttribute("user");
			if (user != null) {
				java.util.Set<String> users = MonitorManager.getInstance().getUsers(MonitorType.Log4j);   
				users.remove(user);
			} 
		} catch (Exception e) {
			LOGGER.error("Error in Session close -> " + e.getMessage());
		}
	}
}
