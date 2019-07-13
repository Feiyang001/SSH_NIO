package com.muke.dao.Impl;

import com.muke.dao.EmployeeDao;
import com.muke.entity.EmployeeEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {


    public EmployeeEntity findByUserNameAndPassword(EmployeeEntity employee){
        String hql = "from EmployeeEntity where username= ? and password=?";
        List<EmployeeEntity> list =(List<EmployeeEntity>) this.getHibernateTemplate().find(hql, employee.getUsername(), employee.getPassword());
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from EmployeeEntity";
        List<Long> list =(List<Long>) this.getHibernateTemplate().find(hql);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<EmployeeEntity> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeEntity.class);
        List<EmployeeEntity> list =(List<EmployeeEntity>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
        return list;
    }

    @Override
    public void save(EmployeeEntity employee) {
        this.getHibernateTemplate().save(employee);
    }

    @Override
    public EmployeeEntity findById(Integer eid) {
        return this.getHibernateTemplate().get(EmployeeEntity.class,eid);
    }

    @Override
    public void updata(EmployeeEntity employee) {
        this.getHibernateTemplate().update(employee);
    }

    @Override
    public void delete(EmployeeEntity entity) {
        this.getHibernateTemplate().delete(entity);
    }


}
