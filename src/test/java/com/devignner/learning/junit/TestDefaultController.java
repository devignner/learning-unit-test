package com.devignner.learning.junit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	}

	@Test
	public void testAddHandler() {
		Request request = new SampleRequest();
		RequestHandler handler = new SampleHandler();
		controller.addHandler(request, handler);
		RequestHandler handler2 = controller.getHandler(request);
		assertSame("Handler we set in controller should be the same handler we get", handler2, handler);
	}
	
	private class SampleRequest implements Request {
		public String getName() {
			return "Test";
		}
	}
	
	private class SampleHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}
	}
	
	private class SampleResponse implements Response {
		// 구현 없음 
	}
}
