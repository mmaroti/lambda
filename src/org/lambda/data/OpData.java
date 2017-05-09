/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public abstract class OpData extends Data {
	public final String symbol;

	public OpData(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public abstract Data call(Data[] args);

	public static abstract class Unary extends OpData {
		public Unary(String symbol) {
			super(symbol);
		}

		public Data call(Data[] args) {
			if (args.length == 1)
				return call(args[0]);
			else
				throw new IllegalArgumentException();
		}

		public abstract Data call(Data arg);
	}

	public static final Unary BOOL_NEG = new Unary("b!") {
		@Override
		public Data call(Data arg) {
			BoolData a = (BoolData) arg;
			return new BoolData(!a.value);
		}
	};

	public static final Unary INT_NEG = new Unary("i-") {
		@Override
		public Data call(Data arg) {
			IntData a = (IntData) arg;
			return new IntData(-a.value);
		}
	};

	public static final Unary PAIR_FST = new Unary("p1") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};

	public static final Unary PAIR_SND = new Unary("p2") {
		@Override
		public Data call(Data arg) {
			PairData a = (PairData) arg;
			return a.left;
		}
	};

	public static abstract class Binary extends OpData {
		public Binary(String symbol) {
			super(symbol);
		}

		public Data call(Data[] args) {
			if (args.length == 2)
				return call(args[0], args[1]);
			else
				throw new IllegalArgumentException();
		}

		public abstract Data call(Data arg1, Data arg2);
	}

	public static final Binary BOOL_AND = new Binary("b&") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg2 : arg1;
		}
	};

	public static final Binary BOOL_OR = new Binary("b|") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a = (BoolData) arg1;
			return a.value ? arg1 : arg2;
		}
	};

	public static final Binary BOOL_EQU = new Binary("b=") {
		@Override
		public Data call(Data arg1, Data arg2) {
			BoolData a1 = (BoolData) arg1;
			BoolData a2 = (BoolData) arg2;
			return new BoolData(a1.value == a2.value);
		}
	};

	public static final Binary INT_ADD = new Binary("i+") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value + a2.value);
		}
	};

	public static final Binary INT_MUL = new Binary("i+") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};

	public static final Binary INT_EQU = new Binary("i=") {
		@Override
		public Data call(Data arg1, Data arg2) {
			IntData a1 = (IntData) arg1;
			IntData a2 = (IntData) arg2;
			return new IntData(a1.value * a2.value);
		}
	};
}
