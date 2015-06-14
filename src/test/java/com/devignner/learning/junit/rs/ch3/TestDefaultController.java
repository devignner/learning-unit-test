package com.devignner.learning.junit.rs.ch3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;




public class TestDefaultController {
	
	private DefaultController controller;
	private Request request;
	private RequestHandler requestHandler;
	
	@Before
	public void instantiate() throws Exception{
		controller = new DefaultController();
		request = new SampleRequest();
		requestHandler = new SampleHandler();
		controller.addHandler(request, requestHandler);
	}
	
	
	@Test
	public void testAddHandler(){
		RequestHandler requestHandler2 = controller.getHandler(request);
		
		assertSame("Handler we set in controller should be the same handler we get", requestHandler2, requestHandler);
	}
	
	@Test
	public void testProcessRequest(){
		Response response = controller.processRequest(request);
		assertNotNull("Must not return a null response", response);
		assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
	}
	
	@Test
	public void testProcessRequestAnswersErrorResponse(){
		 
		SampleRequest request = new SampleRequest("testError");
		SampleExceptionHandler handler = new SampleExceptionHandler();
		controller.addHandler(request, handler);
		Response response = controller.processRequest(request);
		assertNotNull("Must not return a null response", response);
		assertEquals(ErrorResponse.class, response.getClass());
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetHandlerNotDefined(){
		SampleRequest request = new SampleRequest("testNotDefined");
		
		//다음 줄에서 RuntimeException을 발생시킬 것이다.
		controller.getHandler(request);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddRequestDuplicateName(){
		SampleRequest request = new SampleRequest();
		SampleHandler handler = new SampleHandler();
		
		controller.addHandler(request, handler);
	}
	
	@Test(timeout=130)
	@Ignore("Ignore for now until we decide a decent time-limit")
	public void testProcessMultipleRequestTimeout(){
		Request request;
		Response response = new SampleResponse();
		RequestHandler handler = new SampleHandler();
		
		for(int i = 0; i < 99999; i++){
			request = new SampleRequest(String.valueOf(i));
			controller.addHandler(request, handler);
			response = controller.processRequest(request);
			assertNotNull(response);
			assertNotSame(ErrorResponse.class, response.getClass());
		}
	}
	
	
	@Test
	public void testWithHamcrest(){
	}
	
	
	
	private class SampleRequest implements Request {
		
		private static final String DEFAULT_NAME = "Test";
		private String name;
		
		public SampleRequest(String name){
			this.name = name;
		}
		public SampleRequest(){
			this.name = DEFAULT_NAME;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	private class SampleHandler implements RequestHandler{
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}
	}
	
	private class SampleExceptionHandler implements RequestHandler{
		public Response process(Request request) throws Exception {
			throw new Exception("error processing request");
		}
	}
	
	private class SampleResponse implements Response {
		private static final String NAME = "Test";

		public String getName(){
			return NAME;
		}

		@Override
		public boolean equals(Object obj) {
			
			boolean result = false;
			if(obj instanceof SampleResponse){
				result = ((SampleResponse) obj).getName().equals(getName());
				return result;
			}
			return super.equals(obj);
		}

		@Override
		public int hashCode() {
			return NAME.hashCode();
		}
	}
}
