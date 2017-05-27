/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public abstract class Domain extends Data {
	/**
	 * @return if the domain has few elements (no powers)
	 */
	public abstract boolean isSmall();

	/**
	 * @return the size of the small closed domain
	 * @throws InvalidStateException
	 *             if the domain it not small or not closed
	 */
	public abstract int getSize();

	/**
	 * @return true if the two domains are structurally the same and the domain
	 *         variables are named the same
	 */
	@Override
	public abstract boolean equals(Object other);

	@Override
	public abstract String toString();

	public static final Domain INTEGERS = new Domain() {
		@Override
		public boolean isSmall() {
			return false;
		}

		@Override
		public int getSize() {
			return -1;
		}

		@Override
		public boolean equals(Object other) {
			return other == this;
		}

		@Override
		public String toString() {
			return "Int";
		}
	};

	private static boolean isSmall(List domains) {
		while (domains instanceof List.Cons) {
			List.Cons cons = (List.Cons) domains;

			Domain head = (Domain) cons.head;
			if (!head.isSmall())
				return false;

			domains = cons.tail;
		}
		return true;
	}

	public static class Product extends Domain {
		public final List domains;

		public Product(List domains) {
			this.domains = domains;
		}

		@Override
		public boolean isSmall() {
			return isSmall(domains);
		}

		@Override
		public int getSize() {
			int size = 1;
			List doms = domains;

			while (doms instanceof List.Cons) {
				List.Cons cons = (List.Cons) domains;

				Domain head = (Domain) cons.head;
				size *= head.getSize();

				doms = cons.tail;
			}
			return size;
		}

		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Product))
				return false;

			Product prod = (Product) other;
			return domains.equals(prod.domains);
		}

		@Override
		public String toString() {
			return "prod " + domains.toString();
		}
	}

	public static class Union extends Domain {
		public final List domains;

		public Union(List domains) {
			this.domains = domains;
		}

		@Override
		public boolean isSmall() {
			return isSmall(domains);
		}

		@Override
		public int getSize() {
			int size = 0;
			List doms = domains;

			while (doms instanceof List.Cons) {
				List.Cons cons = (List.Cons) domains;

				Domain head = (Domain) cons.head;
				size += head.getSize();

				doms = cons.tail;
			}
			return size;
		}

		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Product))
				return false;

			Product prod = (Product) other;
			return domains.equals(prod.domains);
		}

		@Override
		public String toString() {
			return "union " + domains.toString();
		}
	}

	public static class Arrow extends Domain {
		public final Domain argument;
		public final Domain result;

		public Arrow(Domain argument, Domain result) {
			this.argument = argument;
			this.result = result;
		}

		@Override
		public boolean isSmall() {
			return false;
		}

		@Override
		public int getSize() {
			throw new IllegalStateException();
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Arrow) {
				Arrow o = (Arrow) other;
				return argument.equals(o.argument) && result.equals(o.result);
			} else
				return false;
		}

		@Override
		public String toString() {
			return "arrow " + argument.toString() + " " + result.toString();
		}
	}

	public static class Star extends Domain {
		public final Domain element;

		public Star(Domain element) {
			this.element = element;
		}

		@Override
		public boolean isSmall() {
			return false;
		}

		@Override
		public int getSize() {
			throw new IllegalStateException();
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Star) {
				Star o = (Star) other;
				return element.equals(o.element);
			} else
				return false;
		}

		@Override
		public String toString() {
			return "star " + element.toString();
		}
	}
}
