package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {
    public String encrypt(String password);
    UserModel findByName(String name);
    boolean checkIfValidPassword(String password);
}
