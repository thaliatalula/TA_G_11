package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.repository.UserDB;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
            return true;
        }
        return false;
    }

    @Override
    public UserModel findByUserName(String name) {
        return userDB.findByUsername(name);
    }

    @Override
    public String generateNIG(UserModel user, Date tanggalLahir) {
        String NIG= "G";
        SimpleDateFormat format= new SimpleDateFormat("ddmmyyyy");
        NIG=NIG.concat(format.format(tanggalLahir));        Random rnds = new Random();
        String c = String.valueOf((char) (rnds.nextInt(26) + 'a'));
        String b = String.valueOf((char) (rnds.nextInt(26) + 'a'));
        String stringRandom= (c+b).toUpperCase();
        NIG=NIG.concat(stringRandom);
        Random rnd = new Random();
        int number = rnd.nextInt(999);
        String numberRandom= String.format("%03d", number);
        NIG=NIG.concat(numberRandom);
        NIG=NIG.concat(user.getUuid());
        return NIG;
    }

    @Override
    public String generateNIS(UserModel user, Date tanggalLahir) {
        String NIS= "S";
        SimpleDateFormat format= new SimpleDateFormat("ddmmyyyy");
        NIS=NIS.concat(format.format(tanggalLahir));
        Random rnds = new Random();
        String c = String.valueOf((char) (rnds.nextInt(26) + 'a'));
        String b = String.valueOf((char) (rnds.nextInt(26) + 'a'));
        String stringRandom= (c+b).toUpperCase();
        NIS=NIS.concat(stringRandom);
        Random rnd = new Random();
        int number = rnd.nextInt(999);
        String numberRandom= String.format("%03d", number);
        NIS=NIS.concat(numberRandom);
        NIS=NIS.concat(user.getUuid());
        return NIS;

    }
}
