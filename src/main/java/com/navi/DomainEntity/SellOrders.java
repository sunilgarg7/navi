package com.navi.DomainEntity;

import java.util.Set;
import java.util.TreeSet;

public class SellOrders {
	private Set<OrderInfo> orderSet;

	public SellOrders() {
		this.orderSet = new TreeSet<>((a, b) -> {
			if (a.getId().equals(b.getId())) {
				return 0; // invalid orders, in the absence of clarity, orders with same id will be marked
						  // duplicated and removed hence.
			}

			int priceDiff = a.getPrice().compareTo(b.getPrice());
			if (priceDiff == 0) {
				// A tie on price, check time
				return a.getDate().compareTo(b.getDate());
			}
			return priceDiff;
		});
	}

	public Set<OrderInfo> getOrderSet() {
		return orderSet;
	}
}
