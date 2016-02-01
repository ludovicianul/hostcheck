package com.insidecoding.hostcheck.strategy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.insidecoding.hostcheck.Check;

public enum Connect implements Check {
	INSTANCE;

	@Override
	public void check(String line, int timeout) {
		String[] lineArr = line.split(",");

		// using default port: 80
		if (lineArr.length == 1) {
			connect(lineArr[0], 80, timeout);
		} else {
			for (int i = 1; i < lineArr.length; i++) {

				// we might have an IP range
				if (lineArr[i].indexOf("-") != -1) {
					String[] range = lineArr[i].split("-");
					int left = Integer.parseInt(range[0]);
					int right = Integer.parseInt(range[1]);
					for (int j = left; j <= right; j++) {
						connect(lineArr[0], j, timeout);
					}
				} else {
					connect(lineArr[0], Integer.parseInt(lineArr[i].trim()),
							timeout);
				}
			}
		}
	}

	private static void connect(String url, int port, int timeout) {
		try (Socket sock = new Socket()) {
			SocketAddress endpoint = new InetSocketAddress(url, port);
			sock.connect(endpoint, timeout);
			System.out.println(url + ":" + port + ", " + " able to connect!");
		} catch (IOException e) {
			System.out.println(url + ":" + port + ", "
					+ " NOT able to connect!");
		}
	}

}
