package com.epam.pdp.app;

import java.util.Random;

public class Consumer implements Runnable {

	private static final int MAX_TIME_TO_SLEEP = 1000;

	@Override
	public void run() {
		while (!App.stopConsume) {
			try {
				Random random = new Random();
				Integer valueFromQueue = null;
				Thread.sleep(random.nextInt(MAX_TIME_TO_SLEEP));
				valueFromQueue = App.queue.take();
				System.out.println("Value: " + valueFromQueue + " Size: "
						+ App.queue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
