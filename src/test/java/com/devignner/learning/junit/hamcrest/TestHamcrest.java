package com.devignner.learning.junit.hamcrest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestHamcrest {
	
	private List<Integer> intList;
	
	@Before
	public void init(){
		intList = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			intList.add(i);
		}
	}

	
	@Test
	public void testIs(){
		//값을 직접 비교한다.
		assertThat(intList.get(0), is(0));
		assertThat(intList.get(0), is(equalTo(0)));
		
		// 다른 매처를 감싸서 가독성을 향상시킨다.
		assertThat(intList, is(instanceOf(List.class)));
		assertThat(intList, instanceOf(List.class));
	}
	
	@Test
	public void testEqualTo(){
		
		//equals 메서드로 판단.
		
		List<Integer> testList = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			testList.add(i);
		}
		
		assertThat(testList, equalTo(intList));
	}
	
	@Test
	public void testNot(){
		//단순 값 비교
		assertThat(1, is(not(2)));
		assertThat(1, not(2));
		
		//매처와 혼합
		assertThat(1, not(equalTo(3)));
		assertThat(1, is(not(equalTo(3))));
	}
	
	@Test
	public void testAnything(){
		//항상 매칭되는 매쳐를 반환.
		assertThat("test",  anything());
		assertThat(3.14, anything("test always match"));
		assertThat("test", anything("test always match"));
	}
	
	@Test
	public void testInstanceOf(){
		//is(Class<?>) 대신 사용
		//Class.isInstance 메서드로 판단.
		assertThat(intList, is(instanceOf(ArrayList.class)));
		assertThat(intList, is(instanceOf(List.class)));
		assertThat(intList, instanceOf(ArrayList.class));
	}
	
	
	@Test
	public void testIsA(){
		//is(instanceOf(Class))의 단축버전이다.
		assertThat((ArrayList)intList, isA(ArrayList.class));
		assertThat(intList, isA(List.class));
	}
	
	@Test
	public void testNullValue(){
		assertThat(intList, not(nullValue()));
		assertThat(intList, is(not(nullValue())));
	}
}
