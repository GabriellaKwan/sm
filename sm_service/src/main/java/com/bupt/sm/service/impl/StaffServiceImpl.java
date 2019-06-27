package com.bupt.sm.service.impl;

import com.bupt.sm.dao.DepartmentDao;
import com.bupt.sm.entity.Department;
import com.bupt.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")//自己指定beanName，否则默认时类名首字母小写+Impl后缀
public class StaffServiceImpl implements DepartmentService {

    @Autowired//注解方式自动注入
    private DepartmentDao departmentDao;
    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
