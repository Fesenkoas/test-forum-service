package telran.java47.security.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import telran.java47.security.model.User;

@Component
public class SecuritycontextImpl implements SecurityContext {
Map<String, User> contextMap = new ConcurrentHashMap<>();
	@Override
	public User addUserSession(String sessionId, User user) {
		return contextMap.put(sessionId, user);
	}

	@Override
	public User removeUserSession(String sessionId) {
		return contextMap.remove(sessionId);
	}

	@Override
	public User getUserBySessionId(String sessionId) {
		return contextMap.get(sessionId);
	}

}
