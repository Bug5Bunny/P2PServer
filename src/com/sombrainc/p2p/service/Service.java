package com.sombrainc.p2p.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Service {
	void execute(ObjectInputStream input, ObjectOutputStream output);
}
