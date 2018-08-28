package ru.job4j.junior.control;

import java.util.*;

class Store {

    public static Info diff(final List<User> previous, final List<User> current) {
        List<User> cCurrent = new ArrayList<>(current);
        List<User> cPrev = new ArrayList<>(previous);
        cCurrent.sort(Comparator.comparingInt(User::getId));
        cPrev.sort(Comparator.comparingInt(User::getId));
        List<User> deleted = new ArrayList<>();
        List<User> changed = new ArrayList<>();
        List<User> added = new ArrayList<>();
        Iterator<User> itCurrent = cCurrent.iterator();
        Iterator<User> itPrev = cPrev.iterator();
        User prevUser = null;
        User currUser = null;
        boolean incPrev = false;
        boolean incCurr = false;

        while (itPrev.hasNext() && itCurrent.hasNext()) {
            if (prevUser == null && currUser == null) {
                prevUser = itPrev.next();
                currUser = itCurrent.next();
                continue;
            } else {
                if (incPrev) {
                    prevUser = itPrev.next();
                    incPrev = false;
                }
                if (incCurr) {
                    currUser = itCurrent.next();
                    incCurr = false;
                }
            }
            System.out.println(prevUser);
            System.out.println(currUser);
            if (currUser.getId() < prevUser.getId()) {
                added.add(currUser);
                incCurr = true;
            } else if (currUser.getId() == prevUser.getId()) {
                if (!currUser.equals(prevUser)) {
                    changed.add(currUser);
                }
                incPrev = true;
                incCurr = true;
            } else {
                deleted.add(prevUser);
                incPrev = true;
            }
        }
        if (incPrev && !incCurr) {
            added.add(currUser);
        }
        if (incCurr && !incPrev) {
            deleted.add(prevUser);
        }
        while (itPrev.hasNext()) {
            prevUser = itPrev.next();
            deleted.add(prevUser);
        }
        while (itCurrent.hasNext()) {
            currUser = itCurrent.next();
            added.add(currUser);
        }
        return new Info(changed, added, deleted);
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

        @Override
        public String toString() {
            return "User{" + "id=" + id
                    + ", name='" + name + '\'' + '}';
        }
    }
}