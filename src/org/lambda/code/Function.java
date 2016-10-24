/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.code;

public class Function {
	public final byte[] code;
	public final Function[] funs;

	public Function(byte[] code, Function[] funs) {
		this.code = code;
		this.funs = funs;
	}
}
