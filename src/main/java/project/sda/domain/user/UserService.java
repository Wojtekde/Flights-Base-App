package project.sda.domain.user;

import project.sda.domain.exception.MismatchedPasswordsException;
import project.sda.domain.exception.UserAlreadyExistsException;
import project.sda.domain.exception.UsernameAndPasswordAreNotCorrect;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new UsernameAndPasswordAreNotCorrect();
        }
        return user;
    }

    void register(String username, String password, String repeatedPassword, String firstName, String lastName) {
        if (!password.equals(repeatedPassword)) {
            throw new MismatchedPasswordsException();
        }
        if (userDao.findByUsername(username) != null) {
            throw new UserAlreadyExistsException(username);
        }
        User user = new User(username, password, firstName, lastName);
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findOne(int id) {
        return userDao.findOne(id);
    }

}
