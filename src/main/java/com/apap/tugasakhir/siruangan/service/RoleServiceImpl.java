package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.RoleModel;
import com.apap.tugasakhir.siruangan.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDB roleDB;

    @Override
    public List<RoleModel> findAll() {
        return roleDB.findAll();
    }
}
