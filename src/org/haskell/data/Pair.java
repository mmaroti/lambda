/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public class Pair<FST, SND> {
	private final FST fst;
	private final SND snd;

	public Pair(FST fst, SND snd) {
		this.fst = fst;
		this.snd = snd;
	}

	public static abstract class Case<RET, FST, SND> {
		public abstract RET match(FST fst, SND snd);
	}

	public <RET> RET match(Case<RET, FST, SND> match) {
		return match.match(fst, snd);
	}
}
