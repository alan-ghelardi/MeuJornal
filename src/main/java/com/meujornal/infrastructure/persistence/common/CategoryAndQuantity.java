package com.meujornal.infrastructure.persistence.common;

import java.util.Objects;

public final class CategoryAndQuantity {

	private final String category;
	private final long quantity;

	public CategoryAndQuantity(String category, long quantity) {
		this.category = category;
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public long getQuantity() {
		return quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CategoryAndQuantity)) {
			return false;
		}

		CategoryAndQuantity that = (CategoryAndQuantity) obj;

		return Objects.equals(this.category, that.category)
				&& Objects.equals(this.quantity, that.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, quantity);
	}

	@Override
	public String toString() {
		return String.format("%s (%d)", category, quantity);
	}

}
