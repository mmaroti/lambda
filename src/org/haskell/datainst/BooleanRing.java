/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.typeclass.*;

public class BooleanRing<BOOL> extends Ring<BOOL> {
	private final Logic<BOOL> logic;

	public BooleanRing(Logic<BOOL> logic) {
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
}
