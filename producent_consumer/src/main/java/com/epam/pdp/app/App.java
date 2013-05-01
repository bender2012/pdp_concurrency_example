package com.epam.pdp.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	private static final int QUEUE_SIZE = 10;
	private static final int APP_RUN_PERIOD = 10000;
	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
			QUEUE_SIZE);
	public static volatile boolean stopProduce = false;
	public static volatile boolean stopConsume = false;

	public static void main(String[] args) {
		App app = new App();
		System.out.println("Start");
		Thread producerThread = new Thread(new Producer());
		Thread consumerThread = new Thread(new Consumer());
		try {
			producerThread.start();
			consumerThread.start();
			Thread.sleep(APP_RUN_PERIOD);
			stopProduce = true;
			stopConsume = true;
			System.out.println("Stop");
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finish");
	}

}
