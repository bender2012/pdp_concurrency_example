package com.epam.pdp.app;

public class Worker implements Runnable {

	@Override
	public void run() {
		Connection connection = Connection.getInstance();
		connection.connect();
	}

}
