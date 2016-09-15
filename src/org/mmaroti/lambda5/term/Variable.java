/**
 *	Copyright (C) Miklos Maroti, 2016
 */

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

	@Override
	protected int precedence() {
		return 10;
	}

	@Override
	protected void format(StringBuilder builder, Scope scope) {
		builder.append(scope.lookup(index));
	}
}
