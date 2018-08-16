package ru.job4j.junior.control;


import java.util.PriorityQueue;

public class OrderBook {

    private final String book;
    private PriorityQueue<Order> buy = new PriorityQueue<>();
    private PriorityQueue<Order> sell = new PriorityQueue<>();

    public OrderBook(String book) {
        this.book = book;
    }

    private boolean trySell(Order o) {
        boolean result = false;
        Order bestBuy = buy.peek();
        if (bestBuy != null && bestBuy.getPrice() > o.getPrice()) {
            int bbV = bestBuy.getVolume();
            if (bbV > o.getVolume()) {
                bestBuy.setVolume(bbV - o.getVolume());
                result = true;
            } else if (bbV == o.getVolume()) {
                buy.poll();
                result = true;
            } else {
                o.setVolume(o.getVolume() - bbV);
                buy.poll();
                result = tryBuy(o);
            }
        }
        return result;
    }

    private boolean tryBuy(Order o) {
        boolean result = false;
        Order bestSell = sell.peek();
        if (bestSell != null && bestSell.getPrice() < o.getPrice()) {
            int bsV = bestSell.getVolume();
            if (bsV > o.getVolume()) {
                bestSell.setVolume(bsV - o.getVolume());
                result = true;
            } else if (bsV == o.getVolume()) {
                sell.poll();
                result = true;
            } else {
                o.setVolume(o.getVolume() - bsV);
                sell.poll();
                result = trySell(o);
            }
        }
        return result;
    }

    private void addOrder(Order o) {
        switch (o.getAction()) {
            case ask:
                if (!trySell(o)) {
                    sell.add(o);
                }
                break;
            case bid:
                if (!tryBuy(o)) {
                    buy.add(o);
                }
                break;
            default:
                break;
        }
    }

    private void deleteOrder(Order o) {
        switch (o.getAction()) {
            case ask:
                sell.remove(o);
                break;
            case bid:
                buy.remove(o);
                break;
            default:
                break;
        }
    }

    public void processOrder(Order b) {
        if (b.getBook() != book) {
            throw new IllegalArgumentException();
        }
        switch (b.getType()) {
            case add:
                addOrder(b);
                break;
            case delete:
                deleteOrder(b);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        String s = book + "\n----\n";
        String format = "%-8s | %-8s | %-8s\n";
        s += String.format(format, "Buy", "Price", "Sell");
        String buffer = "";
        int bufferPrice = 0;
        int bufferVolume = 0;
        Order prev = null;
        for (Order o : sell) {
            if (bufferPrice == o.getPrice()) {
                bufferVolume += o.getVolume();
            } else {
                if (prev != null) {
                    buffer = String.format(format, "", bufferPrice, bufferVolume) + buffer;
                }
                bufferPrice = o.getPrice();
                bufferVolume = o.getVolume();
            }
            prev = o;
        }
        if (prev != null) {
            buffer = String.format(format, "", bufferPrice, bufferVolume) + buffer;
        }
        s += buffer;
        for (Order o : buy) {
            s += String.format(format, o.getVolume(), o.getPrice(), "");
        }
        return s;
    }
}
