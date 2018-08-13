package ru.job4j.junior.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Store {

    public static Info diff(final List<User> previous, final List<User> current) {
        List<User> cCopy = current.stream().collect(Collectors.toList());
        List<User> deleted = new ArrayList<>();
        List<User> changed = new ArrayList<>();
        for (User prevU : previous) {
            User tmpUser = null;
            for (User u : cCopy) {
                if (u.equals(prevU)) {
                    tmpUser = u;
                    break;
                } else if (u.getId() == prevU.getId()) {
                    changed.add(prevU);
                    tmpUser = u;
                    break;
                }
            }
            if (tmpUser != null) {
                cCopy.remove(tmpUser);
            } else {
                deleted.add(prevU);
            }
        }
        return new Info(changed, cCopy, deleted);
    }

    public static class User {
        int id;
        String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id
                   && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}