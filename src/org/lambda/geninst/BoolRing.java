/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.geninst;

import org.haskell.typeclass.*;

public class BoolRing<BOOL> extends Ring<BOOL> {
	private final Logic<BOOL> logic;

	public BoolRing(Logic<BOOL> logic) {
		super(logic.FALSE, logic.TRUE);
		this.logic = logic;
	}

	@Override
	public BOOL plus(BOOL elem1, BOOL elem2) {
		return logic.or(logic.and(elem1, logic.not(elem2)),
				logic.and(logic.not(elem1), elem2));
	}

	@Override
	public BOOL prod(BOOL elem1, BOOL elem2) {
		return logic.and(elem1, elem2);
	}

	@Override
	public BOOL negate(BOOL elem) {
		return elem;
	}
}
