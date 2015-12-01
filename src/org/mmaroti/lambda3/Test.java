/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda3;

public class Test {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Calculus calc = new LiteralCalculus();
		Object plus = calc.literal("PLUS");
		Object one = calc.literal("1");
		Object neg = calc.literal("NEG");
		Object equ = calc.literal("EQU");
		Object cond = calc.literal("IF");

		System.out.println(calc.apply(plus, one));
		System.out.println(calc.apply(plus, one, one));

		// (func (x y)
		
		Object x = calc.variable("x");
		Object y = calc.apply(plus, x, calc.apply(neg, one));
		Object f = calc.variable("fact");
		Object t = calc.apply(cond, calc.apply(equ, x, one));
		Object r = calc.apply(t, one, calc.apply(f, y));
		Object fact = calc.lambda(x, r);
	}
}
