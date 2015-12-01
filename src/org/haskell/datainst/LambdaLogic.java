/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;
import org.haskell.lambda.*;

public class LambdaLogic<BOOL> extends Logic<Term<BOOL>> {
	private Logic<BOOL> logic;

	public LambdaLogic(Logic<BOOL> logic) {
		super(new Literal<BOOL>(logic.TRUE), new Literal<BOOL>(logic.TRUE));
		this.logic = logic;
	}

	private final Term<Func<Term<BOOL>, Term<BOOL>>> NOT = new Literal<Func<Term<BOOL>, Term<BOOL>>>(
			new Func<Term<BOOL>, Term<BOOL>>() {
				@Override
				public Term<BOOL> apply(Term<BOOL> arg) {
					return not(arg);
				}
			});

	@Override
	public Term<BOOL> not(Term<BOOL> bool) {
		if (bool instanceof Literal) {
			BOOL b = ((Literal<BOOL>) bool).data;
			return new Literal<BOOL>(logic.not(b));
		} else
			return new Apply<BOOL, BOOL>(NOT, bool);
	}

	private final Term<Func<Term<BOOL>, Term<Func<Term<BOOL>, Term<BOOL>>>>> AND = new Literal<Func<Term<BOOL>, Term<Func<Term<BOOL>, Term<BOOL>>>>>(
			new Func<Term<BOOL>, Term<Func<Term<BOOL>, Term<BOOL>>>>() {
				@Override
				public Term<Func<Term<BOOL>, Term<BOOL>>> apply(
						final Term<BOOL> arg1) {
					return new Literal<Func<Term<BOOL>, Term<BOOL>>>(
							new Func<Term<BOOL>, Term<BOOL>>() {
								@Override
								public Term<BOOL> apply(Term<BOOL> arg2) {
									return and(arg1, arg2);
								}
							});
				}
			});

	@Override
	public Term<BOOL> and(Term<BOOL> bool1, Term<BOOL> bool2) {
		if (bool1 instanceof Literal && bool2 instanceof Literal) {
			BOOL b1 = ((Literal<BOOL>) bool1).data;
			BOOL b2 = ((Literal<BOOL>) bool2).data;
			return new Literal<BOOL>(logic.and(b1, b2));
		} else {
			return new Apply<BOOL, BOOL>(
					new Apply<BOOL, Func<Term<BOOL>, Term<BOOL>>>(AND, bool1),
					bool2);
		}
	}
}
