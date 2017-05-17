/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

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

	public static final BinaryOp[] INSTANCES = new BinaryOp[] { new BinaryOp("arrow") {
		@Override
		public Data call(Data arg1, Data arg2) {
			Domain d1 = (Domain) arg1;
			Domain d2 = (Domain) arg2;
			return new Domain.Arrow(d1, d2);
		}
	}, new BinaryOp("cons") {
		@Override
		public Data call(Data arg1, Data arg2) {
			List l = (List) arg2;
			return new List.Cons(arg1, l);
		}
	} };
}
