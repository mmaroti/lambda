/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Compiler<DATA> {
	public Function<DATA> compile(Term term) {
		return term.compile(this);
	}

	public Function<DATA> variable(final int index) {
		return new Function<DATA>() {
			@Override
			public DATA evaluate(Context<DATA> context) {
				for (int i = 0; i < index; i++)
					context = context.parent;

				return context.data;
			}
		};
	}

	public Function<DATA> lambda(Term body) {
		final Function<DATA> function = compile(body);
		return new Function<DATA>() {
			@Override
			public DATA evaluate(Context<DATA> context) {
				return doLambda(function, context);
			}
		};
	}

	public abstract DATA doLambda(Function<DATA> function, Context<DATA> context);

	public Function<DATA> integer(int value) {
		final DATA data = doInteger(value);
		return new Function<DATA>() {
			@Override
			public DATA evaluate(Context<DATA> context) {
				return data;
			}
		};
	}

	public abstract DATA doInteger(int value);

	public Function<DATA> apply(Term left, Term right) {
		final Function<DATA> f = compile(left);
		final Function<DATA> g = compile(right);
		return new Function<DATA>() {
			@Override
			public DATA evaluate(Context<DATA> context) {
				DATA a = f.evaluate(context);
				DATA b = g.evaluate(context);
				return doApply(a, b);
			}
		};
	}

	public abstract DATA doApply(DATA func, DATA arg);

	public Function<DATA> addition(Term left, Term right) {
		final Function<DATA> f = compile(left);
		final Function<DATA> g = compile(right);
		return new Function<DATA>() {
			@Override
			public DATA evaluate(Context<DATA> context) {
				DATA a = f.evaluate(context);
				DATA b = g.evaluate(context);
				return doAddition(a, b);
			}
		};
	}

	public abstract DATA doAddition(DATA left, DATA right);
}
