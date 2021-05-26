package com.navi.DomainEntity;

import java.util.Date;

/**
 * This class represents the Order Details that we get from standard input 
 * 
 * 
 * @author sgarg
 *
 */
public class OrderInfo {

	/**
	Order format 
	#1 09:45 BAC sell 100 240.10
	#3 09:47 BAC buy 80 238.10
	#7 09:52 TCS buy 10 1001.10
	#9 10:02 BAC buy 150 242.70
	
	**/
	
	String id;
	Date date;
	String ticker; // The Company Name or symbol
	OrderType buyOrSell;
	int quantity;
	Float price;
	
	public OrderInfo(String id, Date date, String ticker, OrderType buyOrSell, int quantity, Float price) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.buyOrSell = buyOrSell;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public OrderType getBuyOrSell() {
		return buyOrSell;
	}
	
	public void setBuyOrSell(OrderType buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", date=" + date + ", ticker=" + ticker + ", buyOrSell=" + buyOrSell
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
