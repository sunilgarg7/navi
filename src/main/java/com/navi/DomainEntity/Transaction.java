package com.navi.DomainEntity;

/**
 * This class represent matched orders transaction 
 * 
 * 
 * @author sgarg
 *
 */
public class Transaction {
		
	    OrderInfo buyer;
	    OrderInfo seller;
	    int quantity;
	    Float executionPrice;

	    public Transaction(OrderInfo party, OrderInfo counterParty, int quantity, Float executionPrice) {
	        this.buyer = party;
	        this.seller = counterParty;
	        this.quantity = quantity;
	        this.executionPrice = executionPrice;
	    }


	    public OrderInfo getBuyer() {
	        return this.buyer;
	    }

	    public OrderInfo getSeller() {
	        return this.seller;
	    }

	    public int getQuantity() {
	        return this.quantity;
	    }

	    public Float getExecutionPrice() {
	        return this.executionPrice;
	    }
}
