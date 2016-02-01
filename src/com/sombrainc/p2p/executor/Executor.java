package com.sombrainc.p2p.executor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.sombrainc.p2p.command.Command;
import com.sombrainc.p2p.command.impl.AddCommand;
import com.sombrainc.p2p.command.impl.ConnectCommand;
import com.sombrainc.p2p.command.impl.FindFileCommand;

public class Executor implements Runnable {
	private final Map<String, Command> COMMANDS;

	private Socket socket;

	public Executor(Socket socket) {
		this.socket = socket;
		COMMANDS = new HashMap<>();
		COMMANDS.put("connect", new ConnectCommand());
		COMMANDS.put("add", new AddCommand());
		COMMANDS.put("findFile", new FindFileCommand());
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
