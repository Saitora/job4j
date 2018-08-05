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
        return Objects.hash(getName(), getChildren(), getBirthday());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getChildren() == user.getChildren()
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getBirthday(), user.getBirthday());
    }

}
