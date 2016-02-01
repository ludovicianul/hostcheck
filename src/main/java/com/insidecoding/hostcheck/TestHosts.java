package com.insidecoding.hostcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.insidecoding.hostcheck.strategy.CheckStrategy;

/**
 * <p>
 * Simple tool to check hosts availability. It supports 3 modes:
 * </p>
 * <ul>
 * <li>lookup: checks if the supplied host names are being correctly resolved by
 * the DNS server(s)</li>
 * <li>reach: simulates a ping. Please note that in this mode you need to run
 * with root/admin privileges in order to simulate the ICMP ping</li>
 * <li>connect: will try to connect host/port(s) supplied in the input file. The
 * format is: host,PORT1,PORT2-PORTX</li>
 * </ul>
 * <p>
 * All output is printed in the console.
 * </p>
 * 
 * @author milie
 *
 */
public class TestHosts {

	public static void main(String... args) throws Exception {
		File f = new File(args[0]);
		String mode = System.getProperty("mode");
		int timeout = Integer.parseInt(System.getProperty("timeout", "2000"));

		String line = null;
		CheckStrategy strategy = CheckStrategy.get(mode);

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			while ((line = reader.readLine()) != null) {
				strategy.doCheck(line, timeout);
			}
		}
	}
}
