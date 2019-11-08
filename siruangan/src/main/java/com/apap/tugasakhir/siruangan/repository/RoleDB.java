package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDB extends JpaRepository<RoleModel, Long> {
    List<RoleModel> findAll();

}
