package com.navi.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.navi.DomainEntity.OrderInfo;
import com.navi.DomainEntity.OrderType;
import com.navi.DomainEntity.Transaction;

public class CliOrderProcessor {
	private List<OrderInfo> orders = new ArrayList<>();

	public OrderInfo parse(String order) {
		/**
		 * Order format
		 *  #1 09:45 BAC sell 100 240.10 
		 *  #3 09:47 BAC buy 80 238.10 
		 *  #7 09:52 TCS buy 10 1001.10
		 **/
		order.trim(); // removing any extra spaces
		String splits[] = order.split(" "); // splitting order against
		String id = splits[0];
		Date date = getDateFromString(splits[1]);
		String stock = splits[2];
		OrderType orderType = splits[3].equalsIgnoreCase("buy") ? OrderType.BUY : OrderType.SELL;
		int quantity = Integer.parseInt(splits[4]);
		float price = Float.parseFloat(splits[5]);
		return new OrderInfo(id, date, stock, orderType, quantity, price);

	}

	public static Date getDateFromString(String dateString) {
		try {
			// we will be getting time as follow 09:45
			String[] dateSplit = dateString.split(":");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateSplit[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(dateSplit[1]));
			return cal.getTime();
		} catch (Exception ex) {
			// If time of order does not comes into required format then Integer.parseInt
			// will throw an exception
			// for now in lack of clarity, we will take todays time.
			return new Date();
		}
	}

	public List<OrderInfo> readFromCLI() {
		// for simplicity, doing like this, for future we might need to think
		// differently.
		System.out.println("Enter total number of orders");

		try (Scanner sc = new Scanner(System.in)) {
			int n = Integer.parseInt(sc.nextLine()); // Either we need to take the Number of entry or we have to define the EOF for the stream
			// In real world we would be continuously getting Orders through API which will be processed as when they get into system if any matching order exists
			System.out.println("Enter order details");
			while (n-- > 0) {
				orders.add(parse(sc.nextLine()));
			}
		} catch (Exception ex) {
			System.out.println("Invalid input format!!: " + ex.getMessage());
		}

		return orders;
	}

	public void writeToCLI(List<Transaction> entries) {
		entries.forEach((entry) -> {
			String output = String.format("%s %d %.2f %s", entry.getBuyer().getId(), entry.getQuantity(),
					entry.getExecutionPrice(), entry.getSeller().getId());
			System.out.println(output);
		});
	}

}
