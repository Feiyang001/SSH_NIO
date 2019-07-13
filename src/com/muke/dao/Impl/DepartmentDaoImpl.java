package com.muke.dao.Impl;

import com.muke.dao.DepartmentDao;
import com.muke.entity.DepartmentEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

    @Override
    public int findCount() {
        String hql = "select count(*) from DepartmentEntity";
        List<Long> list = (List<Long>)this.getHibernateTemplate().find(hql);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<DepartmentEntity> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DepartmentEntity.class);
        List<DepartmentEntity> list = (List<DepartmentEntity>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
        return list;
    }

    @Override
    public void save(DepartmentEntity departmentEntity) {
        this.getHibernateTemplate().save(departmentEntity);
    }

    @Override
    public DepartmentEntity findById(Integer did) {
        return this.getHibernateTemplate().get(DepartmentEntity.class,did);
    }

    @Override
    public void update(DepartmentEntity departmentEntity) {
        this.getHibernateTemplate().update(departmentEntity);
    }

    @Override
    public void delete(DepartmentEntity department) {
        this.getHibernateTemplate().delete(department);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        List<DepartmentEntity> list = (List<DepartmentEntity>) this.getHibernateTemplate().find("from DepartmentEntity");
        return  list;
    }
}
