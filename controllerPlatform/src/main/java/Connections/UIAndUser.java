package Connections;

import java.io.IOException;
import java.util.Set;

import javax.websocket.Session;

import org.json.simple.JSONObject;

import serverws.SessionStorage;

public class UIAndUser {
	static JSONObject jsonObjectUi = new JSONObject();
	static JSONObject jsonObjectUser = new JSONObject();

	public static void UIAndUserData(int carNumber, String phoneNumber, String status, String plateNumber,
			String latitude, String longitude) {
		String msg = null;
		String message = null;

		switch (status) {

		case "warning":

			if (latitude.contains(".962"))
				msg = "https://www.google.co.in/maps/place/12%C2%B057'44.6%22N+77%C2%B036'05.5%22E/@12.962374,77.601529,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d12.962374!4d77.601529";

			else if (latitude.contains(".97"))

				msg = "https://www.google.co.in/maps/place/Health+%26+Glow/@12.9702882,77.6452059,17z/data=!4m13!1m7!3m6!1s0x0:0x0!2zMTLCsDU4JzEzLjAiTiA3N8KwMzgnNTAuNiJF!3b1!8m2!3d12.970283!4d77.6474!3m4!1s0x0:0x817f85abf48db773!8m2!3d12.9711691!4d77.6471025";

			else if (latitude.contains(".90"))

				msg = "https://www.google.co.in/maps/place/Take+A+Break/@12.9068552,77.5873439,17z/data=!4m13!1m7!3m6!1s0x0:0x0!2zMTLCsDU0JzI0LjciTiA3N8KwMzUnMjIuMyJF!3b1!8m2!3d12.90685!4d77.589538!3m4!1s0x0:0xbe93c7c6e181123e!8m2!3d12.9060575!4d77.5889522";

			else if (latitude.contains(".961"))

				msg = "https://www.google.co.in/maps/place/KFC/@12.9611512,77.5265159,17z/data=!4m13!1m7!3m6!1s0x0:0x0!2zMTLCsDU3JzQwLjEiTiA3N8KwMzEnNDMuNCJF!3b1!8m2!3d12.961146!4d77.52871!3m4!1s0x0:0x17fda9155c891f32!8m2!3d12.9609746!4d77.5280533";
			
			message = "Your car is detected in No parking area, Kindly move to other available parking " + msg ;
			break;

		case "penalty":
			message = "You have been fined for parking in No parking zone";
			break;

		}

		jsonObjectUi.put("carNumber", carNumber);
		jsonObjectUi.put("phoneNumber", phoneNumber);
		jsonObjectUi.put("status", status);
		jsonObjectUi.put("plateNumber", plateNumber);
		jsonObjectUi.put("latitude", latitude);
		jsonObjectUi.put("longitude", longitude);

		System.out.println("JSON data to UI API  - " + UIAndUser.getJsonObjectUi());

		// CallUI.callUIAPI(jsonObjectUi.toString());

		Set<Session> sessions = SessionStorage.INSTANCE.getSessions();
		for (Session session : sessions) {
			try {
				session.getBasicRemote().sendText(UIAndUser.getJsonObjectUi().toJSONString());

				// session.getBasicRemote().sendText("Hiii");
				// System.out.println("sent hii");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (status.equalsIgnoreCase("warning") || status.equalsIgnoreCase("penalty")) {

			jsonObjectUser.put("UENum", phoneNumber);
			jsonObjectUser.put("message", message);

			CallUser.callUserAPI(jsonObjectUser.toString());
		}

	}

	public static JSONObject getJsonObjectUi() {
		return jsonObjectUi;
	}

	public static JSONObject getJsonObjectUser() {
		return jsonObjectUser;
	}
}
