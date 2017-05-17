/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public abstract class BinaryOp extends UnaryOp {
	public BinaryOp(String symbol) {
		super(symbol);
	}

	public abstract Data call(Data arg1, Data arg2);

	@Override
	public Data call(final Data arg1) {
		return new UnaryOp("binop?") {
			@Override
			public Data call(Data arg2) {
				return BinaryOp.this.call(arg1, arg2);
			}
		};
	}

	public static final BinaryOp[] INSTANCES = new BinaryOp[] { new BinaryOp("band") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg2 : arg1;
		}
	}, new BinaryOp("bor") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg1 : arg2;
		}
	}, new BinaryOp("bequ") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a1 = (BoolData) arg1;
			BoolData a2 = (BoolData) arg2;
			return new BoolData(a1.value == a2.value);
		}
	}, new BinaryOp("iadd") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value + a2.value);
		}
	}, new BinaryOp("imul") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	}, new BinaryOp("iequ") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new BoolData(a1.value == a2.value);
		}
	}, new BinaryOp("pair") {
		@Override
		public Data call(Data arg1, Data arg2) {
			return new PairData(arg1, arg2);
		}
	} };
}
