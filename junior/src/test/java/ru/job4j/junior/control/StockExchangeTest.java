package ru.job4j.junior.control;

import org.junit.Test;

public class StockExchangeTest {

    private int id = 0;

    private Order genOrder(String book, Order.BidAction action) {
        if (action == null) {
            action = (Math.random() > 0.5) ? Order.BidAction.bid : Order.BidAction.ask;
        }
        return new Order(
            Integer.toString(id++),
            book,
            Order.BidType.add,
            action,
            new Double(Math.random() * 100 + 1).intValue(),
            new Double(Math.random() * 100 + 1).intValue()
        );
    }

    @Test
    public void whenAddOrdersShouldProcessThem() {
        StockExchange stock = new StockExchange();
        Order o1 = genOrder("Gprom", Order.BidAction.bid);
        o1.setPrice(4);
        o1.setVolume(2);
        Order o2 = genOrder("Gprom", Order.BidAction.bid);
        o2.setPrice(1);
        o2.setVolume(1);
        Order o3 = genOrder("Gprom", Order.BidAction.ask);
        o3.setPrice(3);
        o3.setVolume(3);
        Order o4 = genOrder("Gprom", Order.BidAction.ask);
        o4.setPrice(4);
        o4.setVolume(4);
        stock.sendOrder(o1);
        stock.sendOrder(o2);
        stock.sendOrder(o3);
        stock.sendOrder(o4);
        //o1.setType(Order.BidType.delete);
        //stock.sendOrder(o1);
        System.out.println(stock);
    }

}