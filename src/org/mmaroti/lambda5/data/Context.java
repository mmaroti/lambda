/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.data;

import org.mmaroti.lambda5.term.*;

public class Context {
	public final Data data;
	public final Context parent;

	public Context(Data data, Context parent) {
		this.data = data;
		this.parent = parent;
	}

	public Data lookup(int index) {
		Context c = this;
		while (index-- > 0) {
			c = c.parent;
			assert c != null;
		}

		return c.data;
	}

	public static int getExtent(Context context) {
		int d = 0;
		while (context != null) {
			d++;
			context = context.parent;
		}

		return d;
	}

	public static Scope toScope(Context context) {
		if (context == null)
			return null;

		return new Scope("[" + context.data.toString() + "]", toScope(context.parent));
	}
}
