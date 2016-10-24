/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.code;

import org.lambda.term.Context;
import java.util.*;

public abstract class Interpreter<DATA> {
	public abstract DATA closure(Function function, Context<DATA> context);

	public abstract DATA integer(int value);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA addition(DATA left, DATA right);

	public static final byte VARIABLE = 0; // variable index (nullary)
	public static final byte CLOSURE = 1; // function index (nullary)
	public static final byte ADDITION = 2; // (binary)
	public static final byte APPLY = 3; // (binary)

	private List<DATA> stack = new ArrayList<DATA>();

	public void execute(Function function, Context<DATA> context) {
		byte[] code = function.code;
		for (int index = 0; index < code.length; index++) {
			switch (code[index]) {
			case VARIABLE: {
				int n = code[++index] & 0x7FFFFFFF;
				Context<DATA> c = context;
				while (--n >= 0)
					c = c.parent;
				stack.add(c.data);
				break;
			}
			case CLOSURE: {
				int n = code[++index] & 0x7FFFFFFF;
				stack.add(closure(function.funs[n], context));
			}
			case ADDITION: {
				DATA b = stack.remove(stack.size() - 1);
				DATA a = stack.remove(stack.size() - 1);
				stack.add(addition(a, b));
			}
			case APPLY: {
				DATA b = stack.remove(stack.size() - 1);
				DATA a = stack.remove(stack.size() - 1);
				stack.add(apply(a, b));
			}
			}
		}
	}
}
