package com.navi;

import java.util.ArrayList;
import java.util.List;

import com.navi.DomainEntity.OrderInfo;
import com.navi.DomainEntity.Transaction;
import com.navi.processor.CliOrderProcessor;
import com.navi.repo.OrderRepository;

public class NaviStockExchange {

	public static List<OrderInfo> orders = new ArrayList<>();
	
	public static void main(String[] args) {

		CliOrderProcessor processor = new CliOrderProcessor();
		OrderRepository orderRepo = new OrderRepository();

		// Process orders and save them in-memory, at each day we can clear the in memory or define a strategy to do it by itself
		orderRepo.addOrders(processor.readFromCLI());
		// Process the transactions that might take place between different BUY and SELL orders
		List<Transaction> transactions = orderRepo.processOrders();
		// Write the transactions back to the Console.
		processor.writeToCLI(transactions);
	}


}
