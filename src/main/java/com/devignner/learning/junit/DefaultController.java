package com.devignner.learning.junit;

import java.util.HashMap;
import java.util.Map;

public class DefaultController implements Controller {
	private Map<String, RequestHandler> requestHandlers = new HashMap<String, RequestHandler>();
	
	protected RequestHandler getHandler(Request request) {
		if (!this.requestHandlers.containsKey(request.getName())) {
			String message = "Cannot find handler for request name " 
					+ "[" + request.getName() + "]";
			throw new RuntimeException(message);
		}
		
		return requestHandlers.get(request.getName());
	}
	public Response processRequest(Request request) {
		Response response;
		try {
			response = getHandler(request).process(request);
		} catch (Exception exception) {
			response = new ErrorResponse(request, exception);
		}
		return response;
	}

	public void addHandler(Request request, RequestHandler requestHandler) {
		if (requestHandlers.containsKey(request.getName())) {
			throw new RuntimeException("A request handler has " 
					+ "alerady been registered for request name "
					+ "[" + request.getName() + "]");
		} else {
			requestHandlers.put(request.getName(), requestHandler);
		}
		
	}
	
}
