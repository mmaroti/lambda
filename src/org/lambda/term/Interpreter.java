/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Interpreter<DATA> {
	public abstract DATA integer(int value);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA addition(DATA left, DATA right);

	public static class Code {
		public final byte[] code;
		public final int stackSpace;
		public final int variables;

		public Code(byte[] code, int stackSpace, int variables) {
			this.code = code;
			this.stackSpace = stackSpace;
			this.variables = variables;
		}
	}

	public DATA evaluate(Code code, Context<DATA> context) {
		assert code.variables == (context == null ? 0 : context.length);

		@SuppressWarnings("unchecked")
		DATA[] stack = (DATA[]) new Object[code.stackSpace];
		int stackSize = 0;

		for (int i = 0; i < code.code.length; i++)
			switch (code.code[i]) {

			}

		assert stackSize == 1;
		return stack[0];
	}
}
