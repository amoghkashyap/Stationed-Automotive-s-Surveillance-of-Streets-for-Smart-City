package serverws;

import java.util.HashSet;

import javax.websocket.Session;

public enum SessionStorage {
	
	INSTANCE;

	private HashSet<Session> sessions = new HashSet<>();

	public boolean addSession(Session session) {
		return sessions.add(session);
	}

	public boolean removeSession(Session session) {
		return sessions.remove(session);
	}

	public HashSet<Session> getSessions() {
		return this.sessions;
	}

}
