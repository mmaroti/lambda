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
		return new UnaryOp("clo") {
			@Override
			public Data call(Data arg2) {
				return BinaryOp.this.call(arg1, arg2);
			}
		};
	}

	public static final BinaryOp BOOL_AND = new BinaryOp("band") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg2 : arg1;
		}
	};

	public static final BinaryOp BOOL_OR = new BinaryOp("bor") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg1 : arg2;
		}
	};

	public static final BinaryOp BOOL_EQU = new BinaryOp("bequ") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a1 = (BoolData) arg1;
			BoolData a2 = (BoolData) arg2;
			return new BoolData(a1.value == a2.value);
		}
	};

	public static final BinaryOp INT_ADD = new BinaryOp("iadd") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value + a2.value);
		}
	};

	public static final BinaryOp INT_MUL = new BinaryOp("imul") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};

	public static final BinaryOp INT_EQU = new BinaryOp("iequ") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};
}
