package com.epam.pdp.app;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	private static final int QUEUE_SIZE = 10;
	private static final int APP_RUN_PERIOD = 5000;
	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
			QUEUE_SIZE);
	public static volatile boolean stopProduce = false;
	public static volatile boolean stopConsume = false;
	public static List<Integer> list = new LinkedList<Integer>();
	public static int MAX_LIST_SIZE = 10;
	public static final Object lock = new Object();

	public static void main(String[] args) {
		App app = new App();
		System.out.println("Start");
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		try {
			producerThread.start();
			consumerThread.start();
			Thread.sleep(APP_RUN_PERIOD);
			producer.stopRun();
			stopConsume = true;
			System.out.println(">>>>>>>>>>>>>>>>");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SimpleProducer simpleProducer = new SimpleProducer();
		SimpleConsumer simpleConsumer = new SimpleConsumer();
		Thread simpleProducerThread = new Thread(simpleProducer);
		Thread simpleConsumerThread = new Thread(simpleConsumer);
		try {
			simpleProducerThread.start();
			simpleConsumerThread.start();
			Thread.sleep(APP_RUN_PERIOD);
			simpleProducer.stopRun();
			simpleConsumer.stopRun();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finish");
	}

}
