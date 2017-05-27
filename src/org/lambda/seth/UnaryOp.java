/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public abstract class UnaryOp extends NullaryOp {
	public UnaryOp(String symbol) {
		super(symbol);
	}

	public abstract Data call(Data arg);

	public static final UnaryOp[] INSTANCES = new UnaryOp[] { new UnaryOp(
			"List") {
		@Override
		public Data call(Data arg) {
			// TODO Auto-generated method stub
			return null;
		}
	} };
}
