package com.navi.repo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.navi.DomainEntity.BuyOrders;
import com.navi.DomainEntity.OrderInfo;
import com.navi.DomainEntity.OrderType;
import com.navi.DomainEntity.SellOrders;
import com.navi.DomainEntity.Transaction;


public class OrderRepository {

	private  Map<String, BuyOrders> allStocksBuyOrder;
	private  Map<String, SellOrders> allStockSellOrder;
    private  List<Transaction> transactionList;
    
    public OrderRepository() {
        this.allStocksBuyOrder = new HashMap<>();
        this.allStockSellOrder = new HashMap<>();
        this.transactionList = new ArrayList<>();;
    }

    public void addOrders(List<OrderInfo> orders){
        if (orders == null || orders.isEmpty()) {
            return;
        }

        for (OrderInfo order : orders) {
            if (order == null) {
                continue;
            }


            Set<OrderInfo> orderSet = null;
            if (order.getBuyOrSell() == OrderType.BUY) {
            	BuyOrders buyOrders = allStocksBuyOrder.get(order.getTicker());
                if (buyOrders == null) {
                    buyOrders = new BuyOrders();
                    allStocksBuyOrder.put(order.getTicker(), buyOrders);
                }
                orderSet = buyOrders.getOrderSet();
            } else if (order.getBuyOrSell() == OrderType.SELL) {
                SellOrders sellOrders = allStockSellOrder.get(order.getTicker());
                if (sellOrders == null) {
                    sellOrders = new SellOrders();
                    allStockSellOrder.put(order.getTicker(), sellOrders);
                }
                orderSet = sellOrders.getOrderSet();
            }

                orderSet.add(order);
        }
    }


    public List<Transaction> processOrders() {
        if (allStocksBuyOrder == null || allStocksBuyOrder.isEmpty() || allStockSellOrder == null || allStockSellOrder.isEmpty()) {
            return transactionList;
        }

        // process buy orders
        allStocksBuyOrder.forEach((stock, orders) -> {
            Set<OrderInfo> buyOrderSet = orders.getOrderSet();

            if (buyOrderSet == null || buyOrderSet.isEmpty()) {
                return;
            }

            SellOrders sOrderSet = allStockSellOrder.get(stock);
            if (sOrderSet == null) {
                return;
            }

            Set<OrderInfo> sellOrderSet = sOrderSet.getOrderSet();

            buyOrderSet.stream().filter(order -> (order.getQuantity() > 0)).forEach((buy) -> {
                for (OrderInfo sell : sellOrderSet) {
                    if (sell.getQuantity() > 0 && buy.getPrice().compareTo(sell.getPrice()) >= 0) {

                        int qty = 0;
                        if (sell.getQuantity() > buy.getQuantity()) {
                            qty = buy.getQuantity();
                            sell.setQuantity(sell.getQuantity() - buy.getQuantity());
                            buy.setQuantity(0);
                        } else {
                            qty = sell.getQuantity();
                            buy.setQuantity(buy.getQuantity() - sell.getQuantity());
                            sell.setQuantity(0);
                        }

                        // record it in order entry
                        Transaction entry = new Transaction(sell, buy, qty, sell.getPrice());
                        transactionList.add(entry);
                    }
                }
            });
        });

        return transactionList;
    }
	
}
