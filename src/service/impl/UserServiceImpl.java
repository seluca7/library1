package service.impl;

import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (user.getId() != null) {
            return userRepository.update(user);
        } else {
            return userRepository.create(user);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("bad password");
            }
        } else {
            System.out.println("no user with username: " + username);
        }

        return null;
    }

    @Override
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }


}
