package com.sombrainc.p2p.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sombrainc.p2p.repository.ClientRepository;
import com.sombrainc.p2p.service.Service;

public class AddService implements Service {

	@Override
	public void execute(ObjectInputStream input, ObjectOutputStream output) {
		try {
			String IP = input.readUTF();
			List<String> files = Arrays.asList((String[]) input.readObject());
			if (!ClientRepository.containsKey(IP)) {
				ClientRepository.addFiles(IP, new ArrayList<>());
			}
			ClientRepository.addAll(IP, files);
			List<String> result = ClientRepository.getClient(IP);
			System.out.println("Avaliable files from " + IP);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
