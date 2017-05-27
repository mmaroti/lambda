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

			// TODO: implement equality and better toString
		};
	}

	public static final BinaryOp[] INSTANCES = new BinaryOp[] {
			new BinaryOp("arrow") {
				@Override
				public Data call(Data arg1, Data arg2) {
					Domain dom1 = (Domain) arg1;
					Domain dom2 = (Domain) arg2;
					return new Domain.Arrow(dom1, dom2);
				}
			}, new BinaryOp("cons") {
				@Override
				public Data call(Data arg1, Data arg2) {
					List list = (List) arg2;
					return new List.Cons(arg1, list);
				}
			} };
}
