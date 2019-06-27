package com.bupt.sm.controller;

import com.bupt.sm.entity.Department;
import com.bupt.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;//业务层处理对象
    //  /department/list.do     /department_list.jsp
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.getAll();//获取所有的部门信息
        request.setAttribute("LIST",list);//设定LIST键
        request.getRequestDispatcher("../department_list.jsp").forward(request,response);//jsp路径，访问时注意考虑相对路径
    }
}
