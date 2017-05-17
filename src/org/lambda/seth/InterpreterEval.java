/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

import org.lambda.eval.*;

public class InterpreterEval extends Evaluator<Data, Data> {
	@Override
	public Data evaluate(Evaluable<Data> evaluable) {
		assert evaluable.getExtent() == 0;
		return evaluable.evaluate(this, null);
	}

	@Override
	public Data closure(final Context<Data> context, Data type, final Evaluable<Data> body) {
		return new UnaryOp("closure?") {
			@Override
			public Data call(Data arg) {
				return body.evaluate(InterpreterEval.this, new Context<Data>(arg, context));
			}
		};
	}

	@Override
	public Data apply(Data func, Data arg) {
		UnaryOp f = (UnaryOp) func;
		return f.call(arg);
	}

	@Override
	public Data literal(Data value) {
		return value;
	}

	@Override
	public Data primitive(String prim) {
		for (NullaryOp op : NullaryOp.INSTANCES)
			if (op.symbol.equals(prim))
				return op;

		for (UnaryOp op : UnaryOp.INSTANCES)
			if (op.symbol.equals(prim))
				return op;

		for (BinaryOp op : BinaryOp.INSTANCES)
			if (op.symbol.equals(prim))
				return op;

		throw new UnsupportedOperationException();
	}
}
