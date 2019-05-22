package monitoring;

public class VehicleDetection implements Runnable {
	private int carImage;
	private String latitude;
	private String longitude;
	private boolean carExits;
	
	
	
	public VehicleDetection(int carImage, String latitude, String longitude, boolean carExits) {
		super();
		this.carImage = carImage;
		this.latitude = latitude;
		this.longitude = longitude;
		this.carExits = carExits;
	}

	CarMonitoring monitor = new CarMonitoring();

	public void run() {
	
			
			try {
				monitor.checkCar(carImage,latitude,longitude,carExits);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
	}

			
	
}