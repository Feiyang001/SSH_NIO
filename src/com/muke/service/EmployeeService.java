package com.muke.service;

import com.muke.entity.EmployeeEntity;
import com.muke.entity.PageBean;

public interface EmployeeService {

    EmployeeEntity login(EmployeeEntity employee);

    PageBean<EmployeeEntity> findByPage(Integer currentPage);

    void save(EmployeeEntity employee);

    EmployeeEntity findById(Integer eid);

    void updata(EmployeeEntity employee);

    void delete(EmployeeEntity entity);
}
