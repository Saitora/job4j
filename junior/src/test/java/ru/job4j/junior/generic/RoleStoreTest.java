package ru.job4j.junior.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoleStoreTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddOverSizeShouldThrowException() {
        RoleStore rs = new RoleStore(1);
        rs.add(new Role("1"));
        rs.add(new Role("2"));
    }

    @Test
    public void whenReplaceExistedRoleShouldReturnTrue() {
        RoleStore rs = new RoleStore(5);
        rs.add(new Role("1"));
        rs.add(new Role("2"));
        rs.add(new Role("3"));
        rs.add(new Role("4"));
        rs.add(new Role("5"));
        boolean result = rs.replace("3", new Role("abc"));
        assertThat(result, is(true));
    }

    @Test
    public void whenCallFindByIdShouldReturnCorrectObject() {
        RoleStore rs = new RoleStore(5);
        Role role1 = new Role("object_1");
        Role role2 = new Role("object_2");
        Role role3 = new Role("object_3");
        rs.add(role1);
        rs.add(role2);
        rs.add(role3);
        Role result = rs.findById(role2.getId());
        assertThat(result, is(role2));
    }

    @Test
    public void whenCallFindByIdOnEmptyShouldReturnNull() {
        RoleStore rs = new RoleStore(1);
        Role result = rs.findById("1");
        assertThat(result, is((Role) null));
    }

    @Test
    public void whenDeleteExistingElementShouldReturnTrue() {
        RoleStore rs = new RoleStore(1);
        rs.add(new Role("1"));
        boolean result = rs.delete("1");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteNonExistingElementShouldReturnFalse() {
        RoleStore rs = new RoleStore(1);
        rs.add(new Role("1"));
        boolean result = rs.delete("2");
        assertThat(result, is(false));
    }

}
