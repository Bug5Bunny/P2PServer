package com.sombrainc.p2p.command.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.sombrainc.p2p.command.Command;
import com.sombrainc.p2p.util.ClientContainer;

public class FindFileCommand extends Command {

	@Override
	public void execute(ObjectInputStream socketIS, ObjectOutputStream socketOS) {
		try {
			String fileName = socketIS.readUTF();
			String[] fileOwners = getFileOwners(fileName).toArray(new String[0]);
			socketOS.writeObject(fileOwners);
			System.out.println(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getFileOwners(String fileName) {
		List<String> result = new ArrayList<>();
		for (Entry<String, List<String>> entry : ClientContainer.entrySet()) {
			if (entry.getValue().contains(fileName) && isAlive(entry.getKey())) {
				result.add(entry.getKey());
			}
			;
		}
		return result;
	}

	private static boolean isAlive(String ip) {
		try (Socket clientSocket = new Socket(ip, 6667)) {
			ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			outputStream.writeUTF("ping");
			outputStream.flush();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
