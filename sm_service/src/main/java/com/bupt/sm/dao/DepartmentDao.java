package com.bupt.sm.dao;

import com.bupt.sm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("departmentDao")//持久化层的注解
public interface DepartmentDao {
    void insert(Department department);//增
    void delete(Integer id);//删
    void update(Department department);//改
    Department selectById(Integer id);//查，根据id，查找id对应的记录，构造成department对象返回
    List<Department> selectAll();//查，所有的记录，构造department对象，放在List容器中返回
}
