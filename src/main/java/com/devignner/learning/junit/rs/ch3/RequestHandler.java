package com.devignner.learning.junit.rs.ch3;

public interface RequestHandler {
	Response process(Request request) throws Exception;
}
