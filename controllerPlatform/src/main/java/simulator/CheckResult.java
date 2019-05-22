package simulator;

import java.util.Date;

import org.json.simple.JSONObject;

public class CheckResult {
	DetectedCarRequest carRequest = new DetectedCarRequest();
	JSONObject jsonObject = new JSONObject();

	public void loadJSON(int imageId, String latitude, String longitude) {

		jsonObject.put("ImageId", imageId);
		
		jsonObject.put("latitude", latitude);
		jsonObject.put("longitude", longitude);
		jsonObject.put("status", "loading");
		jsonObject.put("parkedTime", (new Date().toString()));

	}

	public void warningMessage() {
		jsonObject.replace("status", "warning");
		carRequest.requestGrpc(jsonObject);

	}

	@SuppressWarnings("unchecked")
	public void carDetected(int imageId) {
		

		jsonObject.replace("status", "detected");
		carRequest.requestGrpc(jsonObject);
	

	}

	public void chargeTow(int ImageId) {
		jsonObject.replace("status", "towing");
		carRequest.requestGrpc(jsonObject);

	}

	public void signPenalty(int imageId) {

		jsonObject.replace("status", "penalty");
		carRequest.requestGrpc(jsonObject);

	}

	public void left(int imageId) {
		jsonObject.replace("status", "left");
		carRequest.requestGrpc(jsonObject);

		
	}

}
