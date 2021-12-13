package repository;
import model.User;
import java.util.List;

public interface UserRepository {
    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    boolean deleteById(Long id);

    User update(User user);

    User create(User user);

}
