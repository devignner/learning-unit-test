package com.devignner.learning.junit.ch1;


//import static junit.framework.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {

	@Test
	public void testAdd(){
		Calculate cal = new Calculate();
		double result = cal.add(10, 50);
		
		/*
		 * 보통은 이렇게 테스트 하엿다.
		 */
		/*if(result != 60){
			throw new IllegalStateException("Bad result" + result);
		}*/
		
		
		/*
		 * Junit 으로 변경
		 */
		assertEquals(60, result, 0);	
		
		
	/*	assertArrayEquals(expecteds, actuals);
		assertEquals // 클래스에 있는 equals 로 연산한다.
		assertSame // 객체 자체 비교 연산
		assertTrue // 항상 트루여야 하는것
		assertNotNull // 반환 값이 Null 이면 안되는것
		
		Assert // 위 메소드가 들어있는 클래스
		
		@Test
		
		Test //Test 클래스
		
		Suite // 테스크를 (묶어서)모아둔 것 수행 
		
		Runner //환경에 맞는 테스트를 하기위해 
		when " add a book"
		given " a bo {%d}"
		then "Result"
		
	 */
		
		
		
		}
}
