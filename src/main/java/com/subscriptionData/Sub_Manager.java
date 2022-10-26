package com.subscriptionData;

import java.io.IOException;

public class Sub_Manager {

	public Sub_Reader getInstanceSubReader() throws IOException {

		Sub_Reader r = new Sub_Reader();
		return r;

	}

	public static Sub_Manager getInstanecSubManager() {
		Sub_Manager sr = new Sub_Manager();
		return sr;

	}

	private Sub_Manager() {
		// TODO Auto-generated constructor stub
	}

}
