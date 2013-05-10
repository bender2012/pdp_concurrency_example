package com.epam.pdp.app;

import java.util.concurrent.Semaphore;

public class Connection {

	private static final long SLEEP_PERIOD = 500;
	private static final int SEMAPHORE_PERMITS = 10;
	private int connectionCounter = 0;

	private Semaphore semaphore;

	private static Connection connection = new Connection();

	private Connection() {
		semaphore = new Semaphore(SEMAPHORE_PERMITS);
	}

	public static Connection getInstance() {
		return connection;
	}

	public void connect() {

		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		synchronized (this) {
			this.connectionCounter++;
			System.out.println("Connection counter: " + connectionCounter);
		}

		try {
			Thread.sleep(SLEEP_PERIOD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			this.connectionCounter--;
		}

		semaphore.release();
	}

}
