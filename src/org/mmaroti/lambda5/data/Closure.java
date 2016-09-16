/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.data;

import org.mmaroti.lambda5.term.*;

public class Closure extends Data {
	public final Context<Data> context;
	public final Term term;

	public Closure(Context<Data> context, Term term) {
		assert term.getExtent() <= Context.getExtent(context);

		this.context = context;
		this.term = term;
	}

	public Data apply(Data argument) {
		return term.evaluate(new Context<Data>(argument, context));
	}

	@Override
	public String toString() {
		return term.toString(Context.toScope(context));
	}
}
