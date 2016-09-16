/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public class IntLiteral extends Term {
	public final int value;

	public IntLiteral(int value) {
		this.value = value;
	}

	@Override
	public int getExtent() {
		return 0;
	}

	@Override
	public Data evaluate(Context<Data> context) {
		return new IntData(value);
	}

	@Override
	protected int precedence() {
		return 10;
	}

	@Override
	protected void format(StringBuilder builder, Context<String> context) {
		builder.append(value);
	}
}
