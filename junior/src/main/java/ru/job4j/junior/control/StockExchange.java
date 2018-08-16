package ru.job4j.junior.control;

import java.util.HashMap;
import java.util.Map;

public class StockExchange {

    private Map<String, OrderBook> stock = new HashMap<>();

    public void sendOrder(Order o) {
        if (!stock.containsKey(o.getBook())) {
            OrderBook oBook = new OrderBook(o.getBook());
            stock.put(o.getBook(), oBook);
        }
        stock.get(o.getBook()).processOrder(o);
    }

    @Override
    public String toString() {
        String s = "Stock Exchange:\n------------------\n";
        for (Map.Entry e : stock.entrySet()) {
            s += e.getValue().toString() + "\n";
        }
        return s;
    }
}
