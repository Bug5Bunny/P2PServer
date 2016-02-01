package com.sombrainc.p2p.command.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sombrainc.p2p.Server;
import com.sombrainc.p2p.command.Command;
import com.sombrainc.p2p.util.ClientContainer;

public class AddCommand extends Command {

	@Override
	public void execute(ObjectInputStream socketIS, ObjectOutputStream socketOS) {
		try {
			String IP = socketIS.readUTF();
			List<String> files = Arrays.asList((String[]) socketIS.readObject());
			if (!ClientContainer.containsKey(IP)) {
				ClientContainer.addFiles(IP, new ArrayList<>());
			}
			ClientContainer.addAll(IP, files);
			List<String> result = ClientContainer.getClient(IP);
			System.out.println("Avaliable files from " + IP);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
