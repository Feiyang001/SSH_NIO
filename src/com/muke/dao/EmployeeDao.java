package com.muke.dao;

import com.muke.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDao {

    int findCount();

    EmployeeEntity findByUserNameAndPassword(EmployeeEntity employee);

    List<EmployeeEntity> findByPage(int begin, int pageSize);

    void save(EmployeeEntity employee);

    EmployeeEntity findById(Integer eid);

    void updata(EmployeeEntity employee);

    void delete(EmployeeEntity entity);
}
