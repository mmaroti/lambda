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

	public static int length(Context<?> context) {
		int a = 0;
		while (context != null) {
			a += 1;
			context = context.parent;
		}
		return a;
	}
}
