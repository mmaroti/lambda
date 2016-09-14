package org.mmaroti.lambda5.data;

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
}
