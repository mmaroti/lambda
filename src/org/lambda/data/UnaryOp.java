/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public abstract class UnaryOp extends Data {
	public final String symbol;

	public UnaryOp(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public abstract Data call(Data arg);

	public static final UnaryOp BOOL_NEG = new UnaryOp("bnot") {
		@Override
		public Data call(Data arg) {
			BoolData a = (BoolData) arg;
			return new BoolData(!a.value);
		}
	};

	public static final UnaryOp INT_NEG = new UnaryOp("ineg") {
		@Override
		public Data call(Data arg) {
			IntData a = (IntData) arg;
			return new IntData(-a.value);
		}
	};

	public static final UnaryOp PAIR_FST = new UnaryOp("pfst") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};

	public static final UnaryOp PAIR_SND = new UnaryOp("psnd") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};
}
