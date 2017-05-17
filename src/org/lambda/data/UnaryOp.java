/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public abstract class UnaryOp extends NullaryOp {
	public UnaryOp(String symbol) {
		super(symbol);
	}

	public abstract Data call(Data arg);

	public static final UnaryOp[] INSTANCES = new UnaryOp[] { new UnaryOp("bnot") {
		@Override
		public Data call(Data arg) {
			BoolData a = (BoolData) arg;
			return new BoolData(!a.value);
		}
	}, new UnaryOp("ineg") {
		@Override
		public Data call(Data arg) {
			IntData a = (IntData) arg;
			return new IntData(-a.value);
		}
	}, new UnaryOp("fst") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	}, new UnaryOp("snd") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	} };
}
