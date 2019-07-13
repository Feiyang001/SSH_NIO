package com.muke.service;

import com.muke.entity.DepartmentEntity;
import com.muke.entity.PageBean;

import java.util.List;

public interface DepartmentService {
    PageBean<DepartmentEntity> findByPage(Integer currentPage);

    void save(DepartmentEntity departmentEntity);

    DepartmentEntity findById(Integer did);

    void update(DepartmentEntity departmentEntity);

    void delete(DepartmentEntity department);

    List<DepartmentEntity> findAll();
}
