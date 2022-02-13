package project.sda.domain.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void shouldCreateUser() {
        // given
        // when
        User user = new User("qwerty", "qwert6", "Jan", "Nowak");

        // then
        assertEquals("qwerty", user.getUsername());
        assertEquals("qwert6", user.getPassword());
    }

    @Test
    public void shouldSetUsername() {
        // given
        User user = new User("qwerty", "qwert6", "Jan", "Nowak");
        String beforeChange = user.getUsername();

        // when
        user.setUsername("qwertyuio");
        String afterChange = user.getUsername();

        // then
        assertEquals("qwerty", beforeChange);
        assertEquals("qwertyuio", afterChange);

    }

    @Test
    public void shouldBeCLIENTCreatedUser() {
        // given
        // when
        User user = new User("qwerty", "qwert6", "Jan", "Nowak");

        //then
        assertEquals(UserRole.CLIENT, user.getRole());
    }
}