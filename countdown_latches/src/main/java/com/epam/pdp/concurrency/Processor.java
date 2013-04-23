package com.epam.pdp.concurrency;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable {

	private final static long TIME_TO_SLEEP = 3000;

	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started");
		try {
			Thread.sleep(TIME_TO_SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}

}
