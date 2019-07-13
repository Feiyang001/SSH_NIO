package com.muke.action;

import com.muke.entity.DepartmentEntity;
import com.muke.entity.EmployeeEntity;
import com.muke.entity.PageBean;
import com.muke.service.DepartmentService;
import com.muke.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

public class EmployeeAction extends ActionSupport implements ModelDriven<EmployeeEntity> {

    private EmployeeEntity employee = new EmployeeEntity();

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public EmployeeEntity getModel() {
        return employee;
    }
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 登录执行的方法
     * @return
     */
    public String login(){
        EmployeeEntity employeeE = employeeService.login(employee);
        if (employeeE == null){
            this.addActionError("用户名或者密码错误");
            return INPUT; //跳转到登录界面
        }
        //登录成功后把用户的信息存在session中
        ActionContext.getContext().getSession().put("employeeExist",employeeE);
        return SUCCESS;
    }

    /**
     * 分页查询员工的方法
     * @return
     */
    private Integer currentPage = 1;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public String findAll(){
        PageBean<EmployeeEntity> pageBean = employeeService.findByPage(currentPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }
    /**
     * 跳转到添加员工的执行的方法
     */
    public String save_UI(){
        //把所有的部门查询出来
        List<DepartmentEntity> list = departmentService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        return "save_UI";
    }

    public String save(){
        employeeService.save(employee);
        return "saveSuccess";
    }

    //编辑员工执行的方法
    public String edit(){
        //根据id查询员工的所有的信息
        EmployeeEntity employeeEntity=employeeService.findById(employee.getEid());
        //查询所有的部门
        List<DepartmentEntity> list = departmentService.findAll();
        //把结果存放在堆栈之中，在页面中可以获取
        ActionContext.getContext().getValueStack().set("list",list);
        return "editSuccess";
    }

    public String updata(){
        employeeService.updata(employee);
        return "updataSuccess";
    }

    public String delete(){
        EmployeeEntity entity = employeeService.findById(employee.getEid());
        employeeService.delete(entity);
        return "deleteSuccess";
    }

}
