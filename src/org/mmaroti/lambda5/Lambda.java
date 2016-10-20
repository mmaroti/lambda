/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5;

public class Lambda extends Term {
	public final String variable;
	public final Term body;

	public Lambda(String variable, Term body) {
		this.variable = variable;
		this.body = body;
	}

	@Override
	public int getExtent() {
		return Math.max(0, body.getExtent() - 1);
	}

	@Override
	public Data evaluate(Context<Data> context) {
		return new Closure(context, this);
	}

	@Override
	protected int precedence() {
		return 3;
	}

	@Override
	protected void format(StringBuilder builder, Context<String> context) {
		builder.append('\\');
		builder.append(variable);
		builder.append('.');
		body.format(builder, new Context<String>(variable, context), 3);
	}
}
