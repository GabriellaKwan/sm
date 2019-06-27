package com.bupt.sm.service;

import com.bupt.sm.entity.Department;

import java.util.List;

public interface DepartmentService {//没有特殊业务功能的业务层，其中的方法会和
    void add(Department department);
    void remove(Integer id);
    void edit(Department department);
    Department get(Integer id);//获取某一个
    List<Department> getAll();//获取所有
}
