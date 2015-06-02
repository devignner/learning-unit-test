package com.devignner.learning.junit;

public interface RequestHandler {
	Response process(Request request) throws Exception;
}
