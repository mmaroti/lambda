/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public abstract class Term {
	public abstract int getExtent();

	public boolean isClosed() {
		return getExtent() == 0;
	}

	public abstract Data evaluate(Context context);

	protected abstract int precedence();

	protected abstract void format(StringBuilder builder, Scope scope);

	protected void format(StringBuilder builder, Scope scope, int prec) {
		boolean b = prec > precedence();
		if (b)
			builder.append('(');

		format(builder, scope);

		if (b)
			builder.append(')');
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		format(builder, null);
		return builder.toString();
	}
}
