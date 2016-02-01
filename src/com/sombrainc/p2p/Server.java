package com.sombrainc.p2p;

import java.io.IOException;
import java.net.ServerSocket;

import com.sombrainc.p2p.executor.Executor;

public class Server {

	public static final int PORT = 6666;

	public static void main(String[] args) throws IOException {
		try {
			ServerSocket ss = new ServerSocket(PORT);
			while (true) {
				new Thread(new Executor(ss.accept())).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
