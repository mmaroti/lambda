package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public class Variable extends Term {
	public final int index;

	public Variable(int index) {
		assert index >= 0;
		this.index = index;
	}

	@Override
	public int getExtent() {
		return index + 1;
	}

	@Override
	public Data evaluate(Context context) {
		return context.lookup(index);
	}
}
