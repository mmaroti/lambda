/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public class Context<DATA> {
	public final DATA data;
	public final Context<DATA> parent;

	public Context(DATA data, Context<DATA> parent) {
		this.data = data;
		this.parent = parent;
	}
}
