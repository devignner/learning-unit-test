package com.devignner.learning.junit.ch3;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.devignner.learning.junit.ch3.DefaultController;
import com.devignner.learning.junit.ch3.Request;
import com.devignner.learning.junit.ch3.RequestHandler;
import com.devignner.learning.junit.ch3.Response;

import static org.junit.Assert.*;

/**
 * 테스트 클래스의 이름에는 Test라는 접두어를 붙인다. 명명규칙이 반드시 필요한 것은 아니지만,
 * 규칙을 잘 정의해두면 테스트 케이스를 다른 클래스와 구분하기 쉬워지므로,
 * 빌드 스크립트에서 테스트를 조작하거나 필터링하기 쉬워진다.
 * @author nnoco
 *
 */
public class TestDefaultController {
	private DefaultController controller;
	private SampleRequest request;
	private SampleHandler handler;
	
	@BeforeClass
	public static void initialize() {
		System.out.println("클래스의 테스트를 수행하기 전");
	}
	
	@AfterClass
	public static void tearDown() {
		System.out.println("클래스의 테스트를 모두 마친 후 수행됨!");
	}
	
	/**
	 * @Before가 붙어있는 메서드는 각 테스트 메서드 사이에서 호출되는 JUnit의 기본 확장 포인트이다.
	 * 그리고 실행할 테스트 케이스가 하나도 없으면 안되므로 더미(dummy) 테스트 메서드를 추가하였다.
	 * @throws Exception
	 */
	@Before
	public void instantiate() throws Exception {
		controller = new DefaultController();
		request = new SampleRequest();
		handler = new SampleHandler();
		controller.addHandler(request, handler);
	}

	@Test
	public void testAddHandler() {
		RequestHandler handler2 = controller.getHandler(request);
		assertSame("Handler we set in controller should be the same handler we get", handler2, handler);
	}
	
	@Test
	public void testProcessRequest() {
		Response response = controller.processRequest(request);
		assertNotNull("Must not return a null response", response);
		assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
	}
	
	@Test
	public void testProcessRequestAnswersErrorResponse() {
		SampleRequest request = new SampleRequest("testError");
		SampleExceptionHandler handler = new SampleExceptionHandler();
		controller.addHandler(request, handler);
		Response response = controller.processRequest(request);
		assertNotNull("Must not return a null response", response);
		assertEquals(ErrorResponse.class, response.getClass());
	}
	
	@Test(expected=RuntimeException.class) 
	public void testGetHandlerNotDefined() {
		SampleRequest request = new SampleRequest("testNotDefined");
		
		controller.getHandler(request);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddRequestDuplicateName() {
		SampleRequest request = new SampleRequest();
		SampleHandler handler = new SampleHandler();
		
		// 다음 줄에서 Runtime Exception을 발생시킬 것이다.
		controller.addHandler(request, handler);
	}
	
	private class SampleRequest implements Request {
		public static final String DEFAULT_NAME = "Test";
		private String name;
		
		public SampleRequest(String name) {
			this.name = name;
		}
		
		public SampleRequest() {
			this.name = DEFAULT_NAME;
		}
		public String getName() {
			return name;
		}
	}
	
	private class SampleHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}
	}
	
	private class SampleResponse implements Response {
		private static final String NAME = "Test";
		
		public String getName() {
			return NAME;
		}
		
		
		/**
		 * Assert 클래스의 equals 메서드는 Object로부터 상속받은 equals 메서드를 사용하여 동등을 비교하므로
		 * Handler의 처리 결과로 받은 Response 클래스가 동등한지 비교하기 위해서
		 * equals 메서드와 hashCode 메서드를 오버라이드한다.
		 */
		public boolean equals(Object object) {
			boolean result = false;
			if (object instanceof SampleResponse) {
				result = ((SampleResponse) object).getName().equals(getName());
			}
			return result;
		}
		
		public int hashCode() {
			return NAME.hashCode();
		}
	}
	
	private class SampleExceptionHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			throw new Exception("error processing request");
		}
	}
}
