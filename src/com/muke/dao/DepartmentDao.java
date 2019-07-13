package com.muke.dao;

import com.muke.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentDao {
    int findCount();

    List<DepartmentEntity> findByPage(int begin, int pageSize);

    void save(DepartmentEntity departmentEntity);

    DepartmentEntity findById(Integer did);

    void update(DepartmentEntity departmentEntity);

    void delete( DepartmentEntity department);

    List<DepartmentEntity> findAll();
}
