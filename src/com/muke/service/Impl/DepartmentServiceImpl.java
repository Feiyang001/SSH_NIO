package com.muke.service.Impl;

import com.muke.dao.DepartmentDao;
import com.muke.entity.DepartmentEntity;
import com.muke.entity.PageBean;
import com.muke.service.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public  class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public PageBean<DepartmentEntity> findByPage(Integer currentPage) {
        PageBean<DepartmentEntity> pageBean = new PageBean<>();
        //封装当前的页数
        pageBean.setCurrentPage(currentPage);
        //封装每页显示的记录数
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        //封装总的记录数
        int totalCount = departmentDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总的页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示的数据
        int begin = (currentPage - 1) *pageSize;
        List<DepartmentEntity> list = departmentDao.findByPage(begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(DepartmentEntity departmentEntity) {
        departmentDao.save(departmentEntity);
    }

    @Override
    public DepartmentEntity findById(Integer did) {
        return departmentDao.findById(did);
    }

    @Override
    public void update(DepartmentEntity departmentEntity) {
        departmentDao.update(departmentEntity);
    }

    @Override
    public void delete(DepartmentEntity department) {
        departmentDao.delete(department);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return departmentDao.findAll();
    }


}
