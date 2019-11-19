package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.RuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    RuanganDB ruanganDB;

    @Override
    public List<RuanganModel> getRuanganList() {
        return ruanganDB.findAll();
    }

    @Override
    public RuanganModel getRuanganById(int id) {
        return ruanganDB.findRuanganById(id);
    }
}
