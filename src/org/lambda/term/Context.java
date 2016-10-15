/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Context<DATA> {
	public final DATA data;
	public final Context<DATA> parent;

	public Context(DATA data, Context<DATA> parent) {
		this.data = data;
		this.parent = parent;
	}
}
