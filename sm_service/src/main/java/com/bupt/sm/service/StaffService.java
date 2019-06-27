package com.bupt.sm.service;

import com.bupt.sm.entity.Staff;

import java.util.List;

public interface StaffService {
    void add(Staff staff);
    void remove(Integer id);
    void edit(Staff staff);
    Staff get(Integer id);//获取某一个
    List<Staff> getAll();//获取所有
}
