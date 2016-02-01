package com.insidecoding.hostcheck.strategy;

import com.insidecoding.hostcheck.Check;

public enum CheckStrategy {
	CONNECT(Connect.INSTANCE), LOOKUP(Lookup.INSTANCE), REACH(Reach.INSTANCE);

	private Check check;

	private CheckStrategy(Check chk) {
		this.check = chk;
	}

	public void doCheck(String line, int timeout) {
		check.check(line, timeout);
	}

	public static CheckStrategy get(String name) {
		for (CheckStrategy strategy : values()) {
			if (strategy.name().equalsIgnoreCase(name)) {
				return strategy;
			}
		}

		throw new IllegalArgumentException("Unknown command: " + name);
	}
}
