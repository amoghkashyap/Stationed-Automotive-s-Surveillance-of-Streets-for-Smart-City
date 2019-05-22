package api;

import mongoDB.DbOperations;

public class API {

	private static DbOperations dbOperations = new DbOperations();

	public static String phNoRecognitionAPI(String plateNo) {
		return dbOperations.phNoRecognitionAPI(plateNo);
	}

	public static String plateRecognitionAPI(int image) {
		return dbOperations.plateRecognitionAPI(image);
	}

}
