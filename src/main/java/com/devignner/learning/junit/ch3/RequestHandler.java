package com.devignner.learning.junit.ch3;

public interface RequestHandler {
	Response process(Request request) throws Exception;
}
