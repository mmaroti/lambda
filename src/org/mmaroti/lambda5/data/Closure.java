package org.mmaroti.lambda5.data;

import org.mmaroti.lambda5.term.*;

public class Closure extends Callable {
	public final Context context;
	public final Term term;

	public Closure(Context context, Term term) {
		assert term.getExtent() <= Context.getExtent(context);

		this.context = context;
		this.term = term;
	}

	@Override
	public Data apply(Data argument) {
		return term.evaluate(new Context(argument, context));
	}
}
