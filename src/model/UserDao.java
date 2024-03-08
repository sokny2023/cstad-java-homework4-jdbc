package model;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User searchById(Integer id);
}
