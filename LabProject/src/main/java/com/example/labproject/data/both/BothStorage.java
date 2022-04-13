package com.example.labproject.data.both;

import com.example.labproject.domain.model.Both;

import java.util.List;

public interface BothStorage {

    List<Both> getAll(String pattern);
    List<Both> getAll1(String pattern);
}
