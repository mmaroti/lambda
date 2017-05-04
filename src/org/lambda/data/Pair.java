/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public class Pair extends Data {
	public final Data left;
	public final Data right;

	public Pair(Data left, Data right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "(" + left.toString() + "," + right.toString() + ")";
	}
}
