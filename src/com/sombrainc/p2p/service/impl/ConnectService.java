package com.sombrainc.p2p.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sombrainc.p2p.service.Service;

public class ConnectService implements Service {

	@Override
	public void execute(ObjectInputStream input, ObjectOutputStream output) {
		try {
			output.writeUTF("Connected to server");
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
