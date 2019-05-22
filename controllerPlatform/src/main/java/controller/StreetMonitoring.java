package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class StreetMonitoring implements Runnable {

	private final Logger log = Logger.getLogger(StreetMonitoring.class.getName());

	@Override
	public void run() {
		log.info("Starting street monitoring...");
		try {
			Server server = ServerBuilder.forPort(17001).addService(new APIImpl()).build();
			Server controllerServer = ServerBuilder.forPort(17002).addService(new ControllerApiImpl()).build();
			controllerServer.start();
			server.start();
			System.out.println("API Server started on port 17001");
			System.out.println("ControllerAPI Server started on port 17002");
			server.awaitTermination();
			controllerServer.awaitTermination();
		} catch (Exception e) {
			log.log(Level.INFO, "Error in grpc server", e);
		}
	}
}
