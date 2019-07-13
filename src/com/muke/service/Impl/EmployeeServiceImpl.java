package com.muke.service.Impl;

import com.muke.dao.EmployeeDao;
import com.muke.entity.EmployeeEntity;
import com.muke.entity.PageBean;
import com.muke.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeEntity login(EmployeeEntity employee) {
        return employeeDao.findByUserNameAndPassword(employee);
    }

    @Override
    public PageBean<EmployeeEntity> findByPage(Integer currentPage) {
        PageBean<EmployeeEntity> pageBean = new PageBean<>();
        //封装当前的页数
        pageBean.setCurrentPage(currentPage);
        //封装每页显示的记录数
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        //封装总的记录数
        int totalCount = employeeDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总的页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示的数据
        int begin = (currentPage - 1) *pageSize;
        List<EmployeeEntity> list = employeeDao.findByPage(begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(EmployeeEntity employee) {
        employeeDao.save(employee);
    }

    @Override
    public EmployeeEntity findById(Integer eid) {
        return employeeDao.findById(eid);
    }

    @Override
    public void updata(EmployeeEntity employee) {
        employeeDao.updata(employee);
    }

    @Override
    public void delete(EmployeeEntity entity) {
        employeeDao.delete(entity);
    }

}
