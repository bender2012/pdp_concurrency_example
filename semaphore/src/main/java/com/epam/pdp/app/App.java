package com.epam.pdp.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	private static final int threadsCount = 200;
	private static final long AWAIT_TERMINATION_PERIOD = 0;
	private static final TimeUnit AWAIT_TERMINATION_TIME_UNIT = TimeUnit.MINUTES;

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < threadsCount; i++) {
			executorService.submit(new Thread(new Worker()));
		}

		executorService.shutdown();
		try {
			executorService.awaitTermination(AWAIT_TERMINATION_PERIOD,
					AWAIT_TERMINATION_TIME_UNIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
