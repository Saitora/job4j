package ru.job4j.junior.multithreading.monitore;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddedTwoUsersStorageSizeShouldBeTwo() {
        UserStorage us = new UserStorage();
        us.add(new User(1, 50));
        us.add(new User(2, 100));
        assertThat(us.size(), is(2));
    }

    @Test
    public void whenDeleteUserShouldBeRemoveFromStorage() {
        UserStorage us = new UserStorage();
        us.add(new User(1, 50));
        us.add(new User(2, 100));
        us.delete(new User(1, 0));
        assertThat(us.size(), is(1));
    }

    @Test
    public void whenUpdateShouldChangeUserAmount() {
        UserStorage us = new UserStorage();
        us.add(new User(1, 50));
        us.update(new User(1, 0));
        assertThat(us.get(1).getAmount(), is(0));
    }

    @Test
    public void whenAddedSameUserShouldNotAddIt() {
        UserStorage us = new UserStorage();
        us.add(new User(1, 50));
        assertThat(us.add(new User(1, 100)), is(false));
    }

    @Test
    public void whenTrancferToSelfShouldReturnFalse() {
        UserStorage us = new UserStorage();
        User user = new User(1, 50);
        us.add(user);
        assertThat(us.transfer(user.getId(), user.getId(), 100), is(false));
    }

    @Test
    public void whenTransferShouldMoveAmountFromFirstToSecond() {
        UserStorage us = new UserStorage();
        User user1 = new User(1, 50);
        User user2 = new User(2, 50);
        us.add(user1);
        us.add(user2);
        us.transfer(user1.getId(), user2.getId(), 50);
        assertThat(user1.getAmount() == 0 && user2.getAmount() == 100, is(true));
    }

}