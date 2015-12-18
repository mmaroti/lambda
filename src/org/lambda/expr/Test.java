/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class Test {
	public static Expression S_COMBINATOR = new Lambda("f", new Lambda("g",
			new Lambda("x", new Application(new Application(new Variable("f"),
					new Variable("x")), new Application(new Variable("g"),
					new Variable("x"))))));

	public static Expression K_COMBINATOR = new Lambda("x", new Lambda("y",
			new Variable("x")));

	public static Expression I_COMBINATOR = new Lambda("x", new Variable("x"));

	public static Expression W_COMBINATOR = new Lambda("x", new Application(
			new Variable("x"), new Variable("x")));

	public static Expression Y_COMBINATOR = new Lambda("f", new Application(
			new Lambda("x", new Application(new Variable("f"), new Application(
					new Variable("x"), new Variable("x")))), new Lambda("x",
					new Application(new Variable("f"), new Application(
							new Variable("x"), new Variable("x"))))));

	public static Expression TEST1 = new Application(new Application(
			new Variable("+"), new Literal(1)), new Literal(2));

	public static void main(String[] args) {
		System.out.println(S_COMBINATOR);
		System.out.println(K_COMBINATOR);
		System.out.println(I_COMBINATOR);
		System.out.println(W_COMBINATOR);
		System.out.println(Y_COMBINATOR);

		System.out.println(TEST1);
		System.out.println(TEST1.variables());
		Expression e = TEST1.substitute("+", K_COMBINATOR);
		System.out.println(e);
		System.out.println(e.simplify());

		Expression SKK = new Application(new Application(S_COMBINATOR,
				K_COMBINATOR), K_COMBINATOR);
		System.out.println(SKK);
		System.out.println(SKK.simplify());
	}
}
