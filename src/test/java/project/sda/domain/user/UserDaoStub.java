package project.sda.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoStub implements UserDao {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User entity) {
//        Integer currentId2 = users.stream().map(User::getId).distinct().findFirst().orElse(null);
//        Integer id = currentId2 != null ? currentId2 + 1 : 1;
//        entity.setId(id);

        Optional<Integer> currentId = users.stream().map(User::getId).distinct().findFirst();
        entity.setId(currentId.map(id -> id + 1).orElse(1));

        users.add(entity);
    }

    @Override
    public void update(User entity) {
        //TODO
    }

    @Override
    public void deleteById(int id) {
//        User user1 = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
//        if(user1!=null){
//            users.remove(user1);
//        }

        Optional<User> userOptional = users.stream().filter(user -> user.getId().equals(id)).findFirst();
        userOptional.ifPresent(users::remove);
    }

    @Override
    public User findOne(int id) {
        return findOneOptional(id).orElse(null);
    }

    @Override
    public Optional<User> findOneOptional(int id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
}
