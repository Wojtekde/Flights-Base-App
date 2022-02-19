package project.sda.domain.user;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import project.sda.domain.exception.UsernameAndPasswordAreNotCorrect;

import static org.junit.Assert.assertEquals;

public class UserLoginTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(new UserDaoStub());
        userService.register("kaiml", "kam12", "kam12", "kamil", "siulik");
    }
    @Test
    public void shouldLogin(){
        // given
        //when
        userService.login("kaiml", "kam12");

        //then
        Integer id = userService.findAll().stream().map(User::getId).distinct().findFirst().orElseThrow(() -> new RuntimeException("Id is null"));
        User user = userService.findOne(id);
        assertEquals("kaiml", user.getUsername());
        assertEquals("kam12", user.getPassword());
    }
    @Test(expected = UsernameAndPasswordAreNotCorrect.class)
    public void shouldThrownExceptionWhenPasswordsAreDifferentAndUsernameAreDifferent(){
        userService.login("kaiml", "kam123");
    }


}
