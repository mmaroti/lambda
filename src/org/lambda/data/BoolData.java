/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.data;

public class BoolData extends Data {
	public final boolean value;

	public BoolData(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}
}
