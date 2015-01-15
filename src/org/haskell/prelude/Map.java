/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class Map<ARG_TYP extends Type, ARG_VAL extends Value<ARG_TYP>, RET_TYP extends Type, RET_VAL extends Value<RET_TYP>>
		extends
		FuncValue<FuncType<ARG_TYP, RET_TYP>, FuncValue<ARG_TYP, ARG_VAL, RET_TYP, RET_VAL>, FuncType<ListType<ARG_TYP>, ListType<RET_TYP>>, FuncValue<ListType<ARG_TYP>, ListValue<ARG_TYP, ARG_VAL>, ListType<RET_TYP>, ListValue<RET_TYP, RET_VAL>>> {

	public Map(
			FuncType<FuncType<ARG_TYP, RET_TYP>, FuncType<ListType<ARG_TYP>, ListType<RET_TYP>>> type) {
		super(type);
	}

	@Override
	public FuncValue<ListType<ARG_TYP>, ListValue<ARG_TYP, ARG_VAL>, ListType<RET_TYP>, ListValue<RET_TYP, RET_VAL>> apply(
			final FuncValue<ARG_TYP, ARG_VAL, RET_TYP, RET_VAL> func) {
		return new FuncValue<ListType<ARG_TYP>, ListValue<ARG_TYP, ARG_VAL>, ListType<RET_TYP>, ListValue<RET_TYP, RET_VAL>>(
				type.ret) {
			@Override
			public ListValue<RET_TYP, RET_VAL> apply(
					ListValue<ARG_TYP, ARG_VAL> arg) {
				return arg
						.caseOf(new ListMatch<ARG_TYP, ARG_VAL, ListValue<RET_TYP, RET_VAL>>() {

							@Override
							public ListValue<RET_TYP, RET_VAL> nill() {
								return new ListNill<RET_TYP, RET_VAL>(type.ret);
							}

							@Override
							public ListValue<RET_TYP, RET_VAL> cons(
									ARG_VAL head,
									ListValue<ARG_TYP, ARG_VAL> tail) {
								return new ListCons<RET_TYP, RET_VAL>(func
										.apply(head), apply(tail));
							}
						});
			}
		};
	}
}
