/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

import org.lambda.exec.*;

public class InterpreterExec extends Executor<Data, Data> {
	@Override
	public Data evaluate(Evaluable<Data> evaluable) {
		return evaluable.evaluate(this, null);
	}

	@Override
	public Data closure(Evaluable<Data> function, Context<Data> context) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Data integer(int value) {
		return new IntData(value);
	}

	@Override
	public Data apply(Data func, Data arg) {
		Function f = (Function) func;
		return f.call(arg);
	}

	@Override
	public Data pair(Data left, Data right) {
		return new Pair(left, right);
	}

	@Override
	public Data addition(Data left, Data right) {
		IntData a = (IntData) left;
		IntData b = (IntData) right;
		return new IntData(a.value + b.value);
	}

	@Override
	public Data literal(Data value) {
		return value;
	}
}
