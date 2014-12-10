/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import org.mmaroti.lambda.Calculus.*;

public class Test {
	public static void print(Term term) {
		System.out.println("term: " + term);
		System.out.println("wrte: " + WriterContext.evaluate(term));
		System.out.println("fold: " + FoldingContext.evaluate(term));

		Variable var;
		if (term.variables.size() >= 1)
			var = term.variables.iterator().next();
		else
			var = new Variable("x");

		Term dat = new Literal(new RuntimeContext.Number(123));
		System.out.println("beta: " + BetaReduction.evaluate(term, var, dat));

		if (term.variables.size() == 0)
			System.out.println("eval: " + RuntimeContext.evaluate(term));

		System.out.println();
	}

	public static void test1() {
		print(LambdaParser.parse("(fun x (call (call ADD x) 12))"));
		print(LambdaParser.parse("(call ADD 12)"));
		print(LambdaParser.parse("(call (fun x (call ADD x)) 12)"));
		// print(LambdaParser.parse("(call (fun x (call x x)) (fun x (call x x)))"));
		print(LambdaParser.parse("(call (call ADD x) x)"));
	}

	public static void main(String[] args) {
		test1();
	}
}
