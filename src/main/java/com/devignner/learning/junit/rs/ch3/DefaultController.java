package com.devignner.learning.junit.rs.ch3;

import java.util.HashMap;
import java.util.Map;

public class DefaultController implements Controller{
	
	private Map<String, RequestHandler> RequestHandlers = new HashMap<String, RequestHandler>();
	
	protected RequestHandler getHandler(Request request){
		if(!this.RequestHandlers.containsKey(request.getName())){
			String message = "Cannot find handler for request name";
			
			throw new RuntimeException(message);
		}
		
		return this.RequestHandlers.get(request.getName());
	}
	
	public Response processRequest(Request request) {
		
		Response response;
		try {
			response  = getHandler(request).process(request);
		} catch (Exception e) {
			response = new ErrorResponse(request, e);
		}
		
		return response;
	}

	
	public void addHandler(Request request, RequestHandler requestHandler) {
		if(this.RequestHandlers.containsKey(request.getName())){
			throw new RuntimeException("A request handler gas already been "
					+ "registered for request name");
		} else {
			this.RequestHandlers.put(request.getName(), requestHandler);
		}
	}
	
}