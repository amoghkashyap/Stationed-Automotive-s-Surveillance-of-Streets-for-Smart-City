package serverws;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.JsonObject;

public class WebSocketEncoder implements Encoder.Text<JsonObject> {
	@Override
	public void destroy() {
		System.out.println("In destroy");
	}

	@Override
	public void init(EndpointConfig arg0) {
		System.out.println("In init");
	}

	@Override
	public String encode(JsonObject response) throws EncodeException {
		return response.toString();
	}
}
