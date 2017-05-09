/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Operator<LIT> extends Term<LIT> {
	public final LIT func;
	public final Term<LIT>[] args;

	public Operator(LIT func, Term<LIT>[] args) {
		assert func != null && args != null;

		this.func = func;
		this.args = args;
	}

	@SuppressWarnings("unchecked")
	public Operator(LIT func, Term<LIT> arg) {
		this(func, (Term<LIT>[]) new Term[] { arg });
	}

	@SuppressWarnings("unchecked")
	public Operator(LIT func, Term<LIT> arg1, Term<LIT> arg2) {
		this(func, (Term<LIT>[]) new Term[] { arg1, arg2 });
	}

	@Override
	public int getExtent() {
		int e = 0;
		for (int i = 0; i < args.length; i++)
			e = Math.max(e, args[i].getExtent());
		return e;
	}

	@Override
	public int getOccurences(int index) {
		int a = 0;
		for (int i = 0; i < args.length; i++)
			a += args[i].getOccurences(index);
		return a;
	}

	@Override
	public Term<LIT> increment(int limit) {
		for (int i = 0; i < args.length; i++) {
			Term<LIT> a = args[i].increment(limit);
			if (a != args[i]) {
				Term<LIT>[] as = args.clone();
				as[i] = a;
				while (++i < args.length)
					as[i] = args[i].increment(limit);

				return new Operator<LIT>(func, as);
			}
		}
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA[] data = (DATA[]) new Object[args.length];
		for (int i = 0; i < args.length; i++)
			data[i] = args[i].evaluate(evaluator, context);

		return evaluator.operator(func, data);
	}
}
