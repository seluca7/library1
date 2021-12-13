package service;
import model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(Long id);

    User login(String username, String password);

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    boolean deleteById(Long id);

    User update(User user);

    User create(User user);
}
