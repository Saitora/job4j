package ru.job4j.junior.generic;

public class Role extends Base {

    protected Role(String id) {
        super(id);
    }

    @Override
    public String toString() {
        String result = "{Role, id='" + getId() + "'}";
        return result;
    }
}
