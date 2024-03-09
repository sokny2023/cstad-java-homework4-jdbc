package model;

import repository.UserRepository;

import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    @Override
    public User searchById(Integer id) {
        List<User> user = UserRepository.getAllUsers().stream()
                .filter(e -> e.getUserId().equals(id)).toList();
        return (User) user;
    }

    @Override
    public User insert(User user) {
        return UserRepository.createUser(user);
    }
}
