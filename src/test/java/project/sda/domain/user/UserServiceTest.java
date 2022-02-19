package project.sda.domain.user;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import project.sda.domain.exception.MismatchedPasswordsException;
import project.sda.domain.exception.UserAlreadyExistsException;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(new UserDaoStub());

    }

    @Test
    public void shouldRegister() {
        // given
        int sizeBeforeRegister = userService.findAll().size();
        userService.register("adamnowak", "adam123", "adam123", "Adam", "Nowak");
        // when
        int sizeAfterRegister = userService.findAll().size();

        // then
        Integer id = userService.findAll().stream().map(User::getId).distinct().findFirst().orElseThrow(() -> new RuntimeException("Id is null"));

        User user = userService.findOne(id);
        assertEquals(0, sizeBeforeRegister);
        assertEquals(sizeBeforeRegister + 1, sizeAfterRegister);
        assertEquals("adamnowak", user.getUsername());
        assertEquals("adam123", user.getPassword());
        assertEquals("Adam", user.getFirstName());
        assertEquals("Nowak", user.getLastName());
    }


    @Test(expected = MismatchedPasswordsException.class)
    public void shouldThrownExceptionWhenPasswordsAreDifferent() {
        // given
        // when
        userService.register("adamnowak", "adam123", "adam123123", "Adam", "Nowak");

        // then
        /*  @Test(expected = MismatchedPasswordsException.class) */
    }

    @Test
    public void shouldThrownExceptionWhenPasswordsAreDifferent_withRule() {
        // given
        thrown.expect(MismatchedPasswordsException.class);
        thrown.expectMessage("The given passwords do not match");

        // when
        userService.register("adamnowak", "adam123", "adam123123", "Adam", "Nowak");

        // then
        /*  @Test(expected = MismatchedPasswordsException.class) */
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrownExceptionWhenUserAlreadyExists() {
        // given
        userService.register("adamnowak", "adam123", "adam123", "Adam", "Nowak");

        // when
        userService.register("adamnowak", "adam123123", "adam123123", "Jan", "Kowalski");

        // then
    }

    @Test
    public void shouldThrownExceptionWhenUserAlreadyExists_withRule() {
        // given
        String username = "adamnowak";

        thrown.expect(UserAlreadyExistsException.class);
        thrown.expectMessage("User with username " + username + " already exists");

        userService.register(username, "adam123", "adam123", "Adam", "Nowak");

        // when
        userService.register(username, "adam123123", "adam123123", "Jan", "Kowalski");

        // then
    }
    @Test
    public void addTwoUsers(){
        //given
        int sizeBeforeRegister = userService.findAll().size();
        userService.register("adamnowak", "adam123", "adam123", "Adam", "Nowak");
        int sizeBeetwenRegister = userService.findAll().size();
        userService.register("kaiml", "kam12", "kam12", "kamil", "siulik");
        //when

        int sizeAfterRegister = userService.findAll().size();

        //then
        Integer id = userService.findAll().stream().map(User::getId).distinct().findFirst().orElseThrow(() -> new RuntimeException("Id is null"));
        User user = userService.findOne(id);
        Integer id1 = userService.findAll().stream().map(User::getId).filter(idd -> !idd.equals(id)).distinct().findFirst().orElseThrow(() -> new RuntimeException("Id is null"));
        User user1 = userService.findOne(id1);
        assertEquals(0, sizeBeforeRegister);
        assertEquals(1, sizeBeetwenRegister);
        assertEquals(sizeBeforeRegister + 2 , sizeAfterRegister);
        assertEquals("adamnowak", user.getUsername());
        assertEquals("adam123", user.getPassword());
        assertEquals("Adam", user.getFirstName());
        assertEquals("Nowak", user.getLastName());
        assertEquals("kaiml", user1.getUsername());
        assertEquals("kam12" , user1.getPassword());
        assertEquals("kamil" , user1.getFirstName());
        assertEquals("siulik" , user1.getLastName());
    }

    @Test
    public void shouldFindAllThreeUsers(){
        //given
        int sizeBeforeRegister = userService.findAll().size();
        userService.register("adamnowak", "adam123", "adam123", "Adam", "Nowak");
        int sizeBeetwenRegister = userService.findAll().size();
        userService.register("kaiml", "kam12", "kam12", "kamil", "siulik");
        userService.register("asdas" , "345432" , "345432", "tomek" ,"bebeasd");
        //when
        int sizeAfterRegister = userService.findAll().size();
        //then
        assertEquals(3, userService.findAll().size());
    }


}
