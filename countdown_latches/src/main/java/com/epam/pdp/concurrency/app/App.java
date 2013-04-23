package com.epam.pdp.concurrency.app;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.pdp.concurrency.Processor;

public class App {

	private final static int DOWN_LATCH_COUNT = 3;
	private final static int NUMBER_OF_THREADS = 3;

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(DOWN_LATCH_COUNT);
		ExecutorService executor = Executors
				.newFixedThreadPool(NUMBER_OF_THREADS);
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			executor.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("Completed");
	}

}
