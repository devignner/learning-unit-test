package com.devignner.learning.junit.hamcrest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

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
	
	
}
