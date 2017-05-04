/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

import org.lambda.eval.*;

public class CalculatorEval extends Evaluator<Data, Data> {
	@Override
	public Data evaluate(Evaluable<Data> evaluable) {
		return evaluable.evaluate(this, null);
	}

	@Override
	public Data closure(Evaluable<Data> function, Context<Data> context) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Data apply(Data func, Data arg) {
		FunData f = (FunData) func;
		return f.call(arg);
	}

	@Override
	public Data pair(Data left, Data right) {
		return new PairData(left, right);
	}

	@Override
	public Data literal(Data value) {
		return value;
	}
}
