package com.sombrainc.p2p;

import java.io.IOException;
import java.net.ServerSocket;

import com.sombrainc.p2p.controller.Controller;
import com.sombrainc.p2p.util.Constant;

public class Server {

	public static void main(String[] args) throws IOException {
		try(ServerSocket ss = new ServerSocket(Constant.SERVERPORT)) {
			while (true) {
				new Thread(new Controller(ss.accept())).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
