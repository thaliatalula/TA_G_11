package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB  extends JpaRepository<UserModel, Integer>{
    UserModel findByUsername(String username);
}
