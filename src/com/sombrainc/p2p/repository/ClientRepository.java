package com.sombrainc.p2p.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ClientRepository {

	private static final Map<String, List<String>> ip2clientsList = new HashMap<>();

	public static synchronized void addFiles(String IP, List<String> list) {
		ip2clientsList.put(IP, list);
	}

	public static synchronized List<String> getClient(String IP) {
		return ip2clientsList.get(IP);
	}

	public static synchronized void addAll(String IP, List<String> list) {
		ip2clientsList.get(IP).addAll(list);
	}

	public static synchronized boolean containsKey(String IP) {
		return ip2clientsList.containsKey(IP);
	}

	public static synchronized Set<Entry<String, List<String>>> entrySet() {
		return ip2clientsList.entrySet();
	}
}
