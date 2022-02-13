package project.sda.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User entity);

    void update(User entity);

    void deleteById(int id);

    User findOne(int id);

    Optional<User> findOneOptional(int id);

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
}
