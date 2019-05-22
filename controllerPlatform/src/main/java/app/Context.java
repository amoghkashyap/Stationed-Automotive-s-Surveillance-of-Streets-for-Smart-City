package app;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import controller.StreetMonitoring;

@WebListener
public class Context implements ServletContextListener {

	private final Logger log = Logger.getLogger(Context.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Initializing the street monitoring...");
		Thread t = new Thread(new StreetMonitoring());
		t.start();
	}

}
