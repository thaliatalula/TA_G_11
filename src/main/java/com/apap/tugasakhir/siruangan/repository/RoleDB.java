package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Integer> {
    List<RoleModel> findAll();

}
