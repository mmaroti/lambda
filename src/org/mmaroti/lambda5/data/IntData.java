/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.data;

public class IntData extends Data {
	public final int value;

	public IntData(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
