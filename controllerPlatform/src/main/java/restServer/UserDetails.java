package restServer;

public class UserDetails {
	
	public UserDetails() {}
	public UserDetails(int carNumber, String phoneNumber, String status, String plateNumber, String latitude,
			String longitude) {
		super();
		this.carNumber = carNumber;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.plateNumber = plateNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	int carNumber; 
	String phoneNumber;
	String status;
	String plateNumber;
	String latitude;
	String longitude;
	public int getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Car Details {CarImageNUmber:" + carNumber + ", Status:" + status + "}";
	}
	

}
