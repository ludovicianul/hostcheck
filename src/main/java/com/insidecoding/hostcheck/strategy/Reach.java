package com.insidecoding.hostcheck.strategy;

import java.io.IOException;
import java.net.InetAddress;

import com.insidecoding.hostcheck.Check;

public enum Reach implements Check {
	INSTANCE;

	@Override
	public void check(String line, int timeout) {
		String[] lineArr = line.split(",");
		try {
			if (InetAddress.getByName(lineArr[0]).isReachable(timeout)) {
				System.out.println(lineArr[0] + " is reachable");
			} else {
				System.out.println(lineArr[0] + " is NOT reachable");
			}
		} catch (IOException e) {
			System.out.println(lineArr[0] + " is NOT reachable");
		}
	}

}
