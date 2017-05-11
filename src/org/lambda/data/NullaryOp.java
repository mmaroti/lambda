/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

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
			new NullaryOp("star"), new NullaryOp("int") };
}
