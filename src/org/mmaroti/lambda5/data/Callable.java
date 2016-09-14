package org.mmaroti.lambda5.data;

public abstract class Callable extends Data {
	public abstract Data apply(Data argument);
}
