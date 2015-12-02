/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class PairEq<BOOL, FST, SND> extends Equality<Pair<FST, SND>, BOOL> {
	private final Equality<FST, BOOL> eq1;
	private final Equality<SND, BOOL> eq2;

	public PairEq(Logic<BOOL> logic, Equality<FST, BOOL> fst,
			Equality<SND, BOOL> snd) {
		super(logic);
		this.eq1 = fst;
		this.eq2 = snd;
	}

	@Override
	public BOOL equ(Pair<FST, SND> data1, final Pair<FST, SND> data2) {
		return data1.match(new Pair.Case<BOOL, FST, SND>() {
			@Override
			public BOOL match(final FST fst1, final SND snd1) {
				return data2.match(new Pair.Case<BOOL, FST, SND>() {
					@Override
					public BOOL match(FST fst2, SND snd2) {
						return logic.and(eq1.equ(fst1, fst2),
								eq2.equ(snd1, snd2));
					}
				});
			}
		});
	}
}
