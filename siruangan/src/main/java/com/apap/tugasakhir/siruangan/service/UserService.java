package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String password);

}
