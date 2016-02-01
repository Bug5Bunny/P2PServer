package com.sombrainc.p2p.command;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Command {
	public abstract void execute(ObjectInputStream socketIS, ObjectOutputStream socketOS);
}
