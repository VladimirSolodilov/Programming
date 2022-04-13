package com.example.labproject.domain.both_service;

import com.example.labproject.domain.model.Both;
import java.util.List;

public interface BothService {

    List<Both> getAllList();
    List<Both> getAllListByNameLike(String name);
    List<Both> getAllList1();

}
