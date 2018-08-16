package ru.job4j.junior.control;

import java.sql.Timestamp;
import java.util.Objects;

public class Order implements Comparable<Order> {

    public enum BidType { add, delete };
    public enum BidAction { bid, ask };

    private String id;
    private String book;
    private BidType type;
    private BidAction action;
    private int price;
    private int volume;
    private Timestamp created;

    public Order(String id, String book, BidType type, BidAction action, int price, int volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public int compareTo(Order o) {
        if ((action != o.getAction()) || (book != o.getBook())) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        result = getPrice() - o.getPrice();
        if (result == 0) {
            result = getCreated().compareTo(o.getCreated());
        }
        return (action == BidAction.bid) ? -result : result;
    }

    public Timestamp getCreated() {
        return created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public BidType getType() {
        return type;
    }

    public void setType(BidType type) {
        this.type = type;
    }

    public BidAction getAction() {
        return action;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{"
                + "id='" + id + '\''
                + ", book='" + book + '\''
                + ", type=" + type
                + ", action=" + action
                + ", price=" + price
                + ", volume=" + volume
                + ", created=" + created
                + '}';
    }
}

