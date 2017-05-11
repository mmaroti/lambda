/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

import org.lambda.eval.*;

public class CalculatorEval extends Evaluator<Data, Data> {
	@Override
	public Data evaluate(Evaluable<Data> evaluable) {
		assert evaluable.getExtent() == 0;
		return evaluable.evaluate(this, null);
	}

	@Override
	public Data closure(Data type, Evaluable<Data> body, Context<Data> context) {
		throw new UnsupportedOperationException();
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
}
