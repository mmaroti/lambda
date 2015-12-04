/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class LambdaLogic<BOOL> extends Logic<Term<BOOL>> {
	private final Logic<BOOL> logic;

	public LambdaLogic(Logic<BOOL> logic) {
		super(new Literal<BOOL>(logic.TRUE), new Literal<BOOL>(logic.TRUE));
		this.logic = logic;
	}

	@Override
	public Term<BOOL> not(Term<BOOL> bool) {
		if (bool instanceof Literal) {
			BOOL b = ((Literal<BOOL>) bool).getData();
			return new Literal<BOOL>(logic.not(b));
		} else if (bool instanceof Apply) {
			return null;
		} else
			return new Apply<BOOL, BOOL>(NOT, bool);
	}

	public final Term<Func<BOOL, BOOL>> NOT = new Literal<Func<BOOL, BOOL>>(
			new Func<BOOL, BOOL>() {
				@Override
				public BOOL apply(BOOL arg) {
					return logic.not(arg);
				}
			});
}
