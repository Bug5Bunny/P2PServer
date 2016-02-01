package com.sombrainc.p2p.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.sombrainc.p2p.service.Service;
import com.sombrainc.p2p.service.impl.AddService;
import com.sombrainc.p2p.service.impl.ConnectService;
import com.sombrainc.p2p.service.impl.FindFileService;
import com.sombrainc.p2p.util.Constant;

public class Controller implements Runnable {
	private static final Map<String, Service> COMMANDS;

	static {
		COMMANDS = new HashMap<>();
		COMMANDS.put(Constant.CONNECT, new ConnectService());
		COMMANDS.put(Constant.ADD, new AddService());
		COMMANDS.put(Constant.FINDFILE, new FindFileService());
	}

	private Socket socket;

	public Controller(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			String reason = input.readUTF();
			COMMANDS.get(reason).execute(input, output);
			output.writeUTF("files added");
			output.flush();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
