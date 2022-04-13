package com.example.labproject.domain.both_service;

import com.example.labproject.data.both.BothStorage;
import com.example.labproject.domain.model.Both;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BothServiceDomain implements BothService {

    @Autowired
    private BothStorage storage;

    @Override
    public List<Both> getAllList() {
        return storage.getAll(null);
    }

    @Override
    public List<Both> getAllListByNameLike(String name) {
        return storage.getAll(name);
    }

    @Override
    public List<Both> getAllList1() {
        return storage.getAll1(null);
    }
}
