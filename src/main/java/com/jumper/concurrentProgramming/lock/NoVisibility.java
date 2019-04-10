package com.jumper.concurrentProgramming.lock;

public class NoVisibility {
	private volatile static boolean ready = false;
	private volatile static int number = 0;

	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready) {
				try {
					Thread.sleep(10);
					System.out.println("sleep");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}