package ru.job4j.junior.generic;

public class User extends Base {

    protected User(String id) {
        super(id);
    }

    @Override
    public String toString() {
        String result = "{User, id='" + getId() + "'}";
        return result;
    }
}
