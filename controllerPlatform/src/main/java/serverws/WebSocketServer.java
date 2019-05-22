package serverws;

import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONObject;

import Connections.UIAndUser;

@ServerEndpoint(value = "/websocketendpoint",  encoders = {WebSocketEncoder.class })
public class WebSocketServer {

	String message;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		SessionStorage.INSTANCE.addSession(session);

		System.out.println("Connection opened with = " + session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Connection closed :" + session.getId());
		SessionStorage.INSTANCE.removeSession(session);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		// JSONObject jsonObjectUi = UIAndUser.getJsonObjectUi();

		System.out.println("Message from the client: " + message);

		/*
		 * return "{ \r\n" + "        \"imgNumber\": 2,\r\n" +
		 * "        \"longitude\": -59,\r\n" + "        \"latitude\": 13,\r\n" +
		 * "        \"PhNumber\": \"+917259825389\",\r\n" +
		 * "        \"PlateNumber\": \"JK 03 7753\",\r\n" +
		 * "        \"Status\": \"warning5555!!\"\r\n" + "    }";
		 */

		/*
		 * try { SessionStorage.INSTANCE.addSession(session); try {
		 * session.getBasicRemote().sendObject(UIAndUser.getJsonObjectUi()); } catch
		 * (EncodeException e) { e.printStackTrace(); }
		 * 
		 * }catch (IOException e) {
		 * System.out.println("Error while sending the response back." +
		 * e.getMessage()); e.printStackTrace(); }
		 */
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}

}
