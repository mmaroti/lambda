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
	public Data closure(Evaluable<Data> function, Context<Data> context) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Data apply(Data func, Data arg) {
		if (func instanceof OpData.Unary) {
			OpData.Unary f = (OpData.Unary) func;
			return f.call(arg);
		} else if (func instanceof OpData.Binary && arg instanceof PairData) {
			OpData.Binary f = (OpData.Binary) func;
			PairData p = (PairData) arg;
			return f.call(p.left, p.right);
		} else
			throw new UnsupportedOperationException();
	}

	@Override
	public Data pair(Data left, Data right) {
		return new PairData(left, right);
	}

	@Override
	public Data literal(Data value) {
		return value;
	}

	@Override
	public Data unaryop(Data func, Data arg) {
		OpData.Unary f = (OpData.Unary) func;
		return f.call(arg);
	}

	@Override
	public Data binaryop(Data func, Data arg1, Data arg2) {
		OpData.Binary f = (OpData.Binary) func;
		return f.call(arg1, arg2);
	}
}
