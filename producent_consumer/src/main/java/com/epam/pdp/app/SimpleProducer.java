package com.epam.pdp.app;

public class SimpleProducer implements Runnable {

	private static final int MAX_LIST_SIZE = 10;
	private static int count = 0;
	private volatile boolean stopRun = false;

	@Override
	public void run() {
		while (!stopRun) {
			synchronized (App.lock) {
				while (App.list.size() == MAX_LIST_SIZE) {
					try {
						App.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				App.list.add(count++);
				App.lock.notify();
			}
		}
	}

	public void stopRun() {
		this.stopRun = true;
	}

}
