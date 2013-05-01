package com.epam.pdp.app;

import java.util.Random;

public class Producer implements Runnable {

	private static final int SLEEP_TIME = 1000;
	private static final int MAX_RANDOM = 1000;
	private volatile boolean stopRun = false;

	@Override
	public void run() {
		Random random = new Random();
		while (!stopRun) {
			try {
				Thread.sleep(random.nextInt(SLEEP_TIME));
				App.queue.add(random.nextInt(MAX_RANDOM));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRun() {
		this.stopRun = true;
	}

}
