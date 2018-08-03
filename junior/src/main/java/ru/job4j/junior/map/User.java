package ru.job4j.junior.map;

import java.util.Calendar;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, Calendar birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public int hashCode() {
        Object[] elements = {getName(), getChildren(), getBirthday()};
        int result = 1;
        for (Object element : elements) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }
}
