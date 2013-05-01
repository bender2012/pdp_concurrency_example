package com.epam.pdp.app;

import java.util.Random;

public class Producer implements Runnable {

	private static final int SLEEP_TIME = 1000;
	private static final int MAX_RANDOM = 1000;

	@Override
	public void run() {
		Random random = new Random();
		while (!App.stopProduce) {
			try {
				Thread.sleep(random.nextInt(SLEEP_TIME));
				App.queue.add(random.nextInt(MAX_RANDOM));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
