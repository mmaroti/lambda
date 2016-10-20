/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5;

public class Context<DATA> {
	public final DATA data;
	public final Context<DATA> parent;

	public Context(DATA data, Context<DATA> parent) {
		this.data = data;
		this.parent = parent;
	}

	public DATA lookup(int index) {
		Context<DATA> c = this;
		while (index-- > 0) {
			c = c.parent;
			assert c != null;
		}

		return c.data;
	}

	public static <DATA> int getExtent(Context<DATA> context) {
		int d = 0;
		while (context != null) {
			d++;
			context = context.parent;
		}

		return d;
	}

	public static Context<String> toScope(Context<Data> context) {
		if (context == null)
			return null;

		return new Context<String>("[" + context.data.toString() + "]",
				toScope(context.parent));
	}
}
