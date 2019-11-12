package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDB userDB;

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String hashedPassword=passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public boolean checkIfUsernameTaken(UserModel user) {
        if(userDB.existsUserModelByUsernameIsLike(user.getUsername())){
            if(userDB.findByUsername(user.getUsername()).equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserModel findByUserName(String name) {
        return userDB.findByUsername(name);
    }
}
