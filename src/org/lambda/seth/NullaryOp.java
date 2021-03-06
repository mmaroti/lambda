/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public class NullaryOp extends Data {
	public final String symbol;

	public NullaryOp(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "#" + symbol;
	}

	public static final NullaryOp[] INSTANCES = new NullaryOp[] {
			new NullaryOp("dom"), new NullaryOp("nill") };

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NullaryOp))
			return false;

		NullaryOp op = (NullaryOp) other;
		return symbol.equals(op.symbol);
	}
}
