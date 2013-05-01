package com.epam.pdp.app;

public class SimpleConsumer implements Runnable {

	private volatile boolean stopRun = false;

	@Override
	public void run() {
		while (!stopRun) {
			synchronized (App.lock) {
				try {
					while (App.list.size() == 0) {
						App.lock.wait();
					}
					System.out.print("List size: " + App.list.size());
					System.out.println(" Element: " + App.list.remove(0));
					Thread.sleep(100);
					App.lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void stopRun() {
		this.stopRun = true;
	}

}
