package com.insidecoding.hostcheck.strategy;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.insidecoding.hostcheck.Check;

public enum Lookup implements Check {
	INSTANCE;

	@Override
	public void check(String line, int timeout) {
		String[] lineArr = line.split(",");
		try {
			InetAddress addr = InetAddress.getByName(lineArr[0]);
			if (addr.getHostAddress().equals(lineArr[0])) {
				System.out.println(lineArr[0] + " skipping");
			} else {
				InetAddress[] addresses = InetAddress.getAllByName(lineArr[0]);
				StringBuilder builder = new StringBuilder();
				builder.append("[");
				for (InetAddress address : addresses) {
					builder.append("{").append(address.getHostAddress() + " | " + address.getHostName() + " | "
							+ address.getCanonicalHostName()).append("}");
				}
				builder.append("]");
				System.out.println(lineArr[0] + " " + builder);
			}
		} catch (UnknownHostException e) {
			System.out.println(lineArr[0] + " not able to resolve");
		}
	}

}
