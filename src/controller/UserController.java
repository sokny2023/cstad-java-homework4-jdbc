package controller;

import model.User;
import model.UserDao;
import model.UserDaoImpl;

import java.util.List;

public class UserController {
    private final UserDao userDao = new UserDaoImpl();

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User searchById(Integer id){
        return userDao.searchById(id);
    }
}
