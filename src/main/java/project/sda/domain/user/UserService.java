package project.sda.domain.user;

import project.sda.domain.exception.UsernameAndPasswordAreNotCorrect;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    User login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        throwExceptionWhenCredentialsAreWrong(user);
        return user;
    }

    private void throwExceptionWhenCredentialsAreWrong(User user) {
        if (user == null) {
            throw new UsernameAndPasswordAreNotCorrect();
        }
    }

}
