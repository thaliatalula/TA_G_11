package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;

import java.util.Date;

public interface UserService {

    UserModel addUser(UserModel user);
    String encrypt(String password);
    boolean checkIfUsernameTaken(UserModel user);
    UserModel findByUserName(String name);
    String generateNIS(UserModel user, Date tanggalLahir);
    String generateNIG(UserModel user, Date tanggalLahir);
    String generateNIP(UserModel user, Date tanggalLahir);

}
