package com.sombrainc.p2p.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ClientContainer {

	private static Map<String, List<String>> clients = new HashMap<>();

	public static synchronized void addFiles(String IP, List<String> list) {
		clients.put(IP, list);
	}

	public static synchronized List<String> getClient(String IP) {
		return clients.get(IP);
	}

	public static synchronized void addAll(String IP, List<String> list) {
		clients.get(IP).addAll(list);
	}

	public static synchronized boolean containsKey(String IP) {
		return clients.containsKey(IP);
	}

	public static synchronized Set<Entry<String, List<String>>> entrySet() {
		return clients.entrySet();
	}
}
