/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class Context<DATA> {
	public final DATA data;
	public final Context<DATA> parent;
	public final int length;

	public Context(DATA data, Context<DATA> parent) {
		this.data = data;
		this.parent = parent;
		this.length = parent == null ? 1 : parent.length + 1;
	}
}
