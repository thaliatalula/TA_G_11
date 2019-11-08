package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {

    UserModel addUser(UserModel user);
    String encrypt(String password);
    boolean checkIfUsernameTaken(UserModel user);

}
