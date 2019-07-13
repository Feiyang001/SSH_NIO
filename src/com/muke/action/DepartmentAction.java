package com.muke.action;

import com.muke.entity.DepartmentEntity;
import com.muke.entity.PageBean;
import com.muke.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements ModelDriven<DepartmentEntity> {

    private DepartmentEntity departmentEntity = new DepartmentEntity();

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    private DepartmentService departmentService ;

    @Override
    public DepartmentEntity getModel() {
        return departmentEntity;
    }

    /**
     * 分页查询部门
     */
    private Integer currentPage= 1;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String findAll(){
        PageBean<DepartmentEntity> departmentPageBean = departmentService.findByPage(currentPage);
        //将pageBean 存在值栈中
        ActionContext.getContext().getValueStack().push(departmentPageBean);
        return "findAll";
    }

    public String saveUI(){

        return "saveUI";
    }

    public String save(){
        departmentService.save(departmentEntity);
        return "saveSuccess";
    }

    public String update(){
        departmentService.update(departmentEntity);
        return "updateSuccess";
    }

    public String eidit(){
        DepartmentEntity department = departmentService.findById(departmentEntity.getDid());
        return "editSuccess";
    }
    public String delete(){
        DepartmentEntity department = departmentService.findById(departmentEntity.getDid());
        departmentService.delete(department);
        return "deleteSuccess";
    }


    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


}
