/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public abstract class FunData extends Data {
	public final String symbol;

	public FunData(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public abstract Data call(Data arg);

	public static final FunData BOOL_NEG = new FunData("b!") {
		@Override
		public Data call(Data arg) {
			BoolData a = (BoolData) arg;
			return new BoolData(!a.value);
		}
	};

	public static final FunData INT_NEG = new FunData("i-") {
		@Override
		public Data call(Data arg) {
			IntData a = (IntData) arg;
			return new IntData(-a.value);
		}
	};

	public static final FunData PAIR_FST = new FunData("p1") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};

	public static final FunData PAIR_SND = new FunData("p2") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};

	private static abstract class BinaryOp extends FunData {
		public BinaryOp(String symbol) {
			super(symbol);
		}

		public abstract Data call(Data arg1, Data arg2);

		@Override
		public Data call(Data arg) {
			PairData pair = (PairData) arg;
			return call(pair.left, pair.right);
		}
	}

	public static final FunData BOOL_AND = new BinaryOp("b&") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg2 : arg1;
		}
	};

	public static final FunData BOOL_OR = new BinaryOp("b|") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg1 : arg2;
		}
	};

	public static final FunData BOOL_EQU = new BinaryOp("b=") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a1 = (BoolData) arg1;
			BoolData a2 = (BoolData) arg2;
			return new BoolData(a1.value == a2.value);
		}
	};

	public static final FunData INT_ADD = new BinaryOp("i+") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value + a2.value);
		}
	};

	public static final FunData INT_MUL = new BinaryOp("i+") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};

	public static final FunData INT_EQU = new BinaryOp("i=") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};
}
