package project.sda.domain.user;

import java.util.*;

public class UserDaoStubMap implements UserDao {

    Map<Integer, User> users = new HashMap<>();

    @Override
    public void save(User entity) {
        Optional<Integer> previousId = users.keySet().stream().distinct().findFirst();
        Integer currentId = previousId.map(id -> id + 1).orElse(1);
        entity.setId(currentId);

        users.put(currentId, entity);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User findOne(int id) {
        return users.get(id);
    }

    @Override
    public Optional<User> findOneOptional(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return users.values().stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User findByUsername(String usernameParam) {
        return null;
    }
}
