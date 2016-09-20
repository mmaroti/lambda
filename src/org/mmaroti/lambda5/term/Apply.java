/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public class Apply extends Term {
	public final Term function;
	public final Term argument;

	public Apply(Term function, Term argument) {
		this.function = function;
		this.argument = argument;
	}

	@Override
	public int getExtent() {
		return Math.max(function.getExtent(), argument.getExtent());
	}

	@Override
	public Data evaluate(Context<Data> context) {
		Closure clo = (Closure) function.evaluate(context);
		Data arg = argument.evaluate(context);

		Lambda lam = (Lambda) clo.term;
		return lam.body.evaluate(new Context<Data>(arg, clo.context));
	}

	@Override
	protected int precedence() {
		return 5;
	}

	@Override
	protected void format(StringBuilder builder, Context<String> context) {
		function.format(builder, context, 5);
		argument.format(builder, context, 6);
	}
}
