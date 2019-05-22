package simulator;

import java.io.IOException;

import controller.APIImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import monitoring.VehicleDetection;

public class CarSimulation {
	public static void main(String[] args) throws InterruptedException, IOException {
		VehicleDetection vehicle1 = new VehicleDetection(1, "12.962374", "77.601529", true);
		VehicleDetection vehicle2 = new VehicleDetection(2, "12.970283", " 77.647449", true);
		VehicleDetection vehicle3 = new VehicleDetection(3, "12.906850", "77.589538", true);
		VehicleDetection vehicle4 = new VehicleDetection(4, "12.961146", " 77.528710", true);
/*		VehicleDetection vehicle5 = new VehicleDetection(5, "11", "50", true);
		VehicleDetection vehicle6 = new VehicleDetection(6, "14", "50", true);
		VehicleDetection vehicle7 = new VehicleDetection(7, "14", "75", true);
		VehicleDetection vehicle8 = new VehicleDetection(8, "13", "50", true);*/
		
		
		Thread car1 = new Thread(vehicle1);
		Thread car2 = new Thread(vehicle2);
		Thread car3 = new Thread(vehicle3);
		Thread car4 = new Thread(vehicle4);
		/*Thread car5 = new Thread(vehicle5);
		Thread car6 = new Thread(vehicle6);
		Thread car7 = new Thread(vehicle7);
		Thread car8 = new Thread(vehicle8);*/
		

		car1.start();
		car2.sleep(10000);
		car2.start();
		car3.sleep(15000);
		car3.start();
		car4.sleep(20000);
		car4.start();
	/*	car5.sleep(10000);
		car5.start();
		car6.sleep(15000);
		car6.start();
		car7.sleep(15000);
		car7.start();
		car8.sleep(20000);
		car8.start();
*/
	}

}
