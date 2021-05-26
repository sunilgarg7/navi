package com.navi.DomainEntity;

import java.util.Set;
import java.util.TreeSet;

public class BuyOrders {

	private Set<OrderInfo> orderSet;

	public BuyOrders() {
		this.orderSet = new TreeSet<>((a, b) -> {
			if (a.getId().equals(b.getId())) {
				return 0; // Invalid orders // though should not be the case,
				// In absence of clear instructions marking them duplicate and removing them
			}

			int result = a.getDate().compareTo(b.getDate());
			if (result == 0) {
				// orders with same time, should not be mark invalid unless they are of same ID.
				// in absence of clear instructions, returning id comparison, if id will be same
				// than order wont be considered as above check
				return a.getId().compareTo(b.getId());
			}
			return result;
		});
	}

	public Set<OrderInfo> getOrderSet() {
		return orderSet;
	}
}
