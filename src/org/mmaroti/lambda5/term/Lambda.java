package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public class Lambda extends Term {
	public final Term body;

	public Lambda(Term body) {
		this.body = body;
	}

	@Override
	public int getExtent() {
		return Math.max(0, body.getExtent() - 1);
	}

	@Override
	public Data evaluate(Context context) {
		return new Closure(context, this);
	}
}
