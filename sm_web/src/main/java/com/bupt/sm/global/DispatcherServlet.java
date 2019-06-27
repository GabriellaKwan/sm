package com.bupt.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Dispatcher核心控制器
 * 流程：
 * 1.初始化时加载IOC容器
 * 2.对每次用户请求，获取用户请求的路径
 * 3.通过url解析获取beanName和方法名
 * 4.在IOC容器中获取对象，通过反射把方法拿出来，调用该方法
 **/
public class DispatcherServlet extends GenericServlet {
    //初始化，加载IOC容器，从context获取上下文
    private ApplicationContext context;
    public void init() throws ServletException {
        super.init();
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;//参数servletRequest没有办法获取用户请求的url，因此强转
        HttpServletResponse response = (HttpServletResponse) servletResponse;//强转

        /*规范用户实际开发控制器的相应规则
				添加一个员工等的访问路径
				/staff/add.do        /login.do
               staff/add.do        login.do
               staff操作的beanName应该叫staffController
			   staffController
               public void add(HttpServletRequest request, HttpServletResponse response){}
         */

        //获取用户请求的url，request.getServletPath()获取的是/staff/add.do
        //根'/'不要，直接截取子串substring，得到staff/add.do    或者    login.do
        String path = request.getServletPath().substring(1);
        String beanName = null;
        String methodName = null;
        //查找'/'符号，根据是否有'/'符号，区分普通模块如/add.do和特殊模块，比如登录登出login.do
        int index = path.indexOf('/');
        if (index != -1) {//即staff/add.do 这种格式
            beanName = path.substring(0, index) + "Controller";
            methodName = path.substring(index + 1, path.indexOf(".do"));//获取方法名，如add，截取indexl'/'和'.do'之间的方法名
        } else {//login.do格式
            beanName = "selfController";
            methodName = path.substring(0, path.indexOf(".do"));//如login，从开头到'.do'之间的方法名
        }

        //从IOC容器中获取对象,从context获取上下文
//        每次访问都需要执行下面这句，加载配置文件构造IOC容器，建议去掉，在前面重写servlet的init（）方法
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Object obj = context.getBean(beanName);//根据beanName获取对象obj
        try {//获取方法
            Method method = obj.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);//通过类的getMethod()获取方法名
            method.invoke(obj,request,response);//invoke方法，对象obj，参数
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {//invoke参数request产生的异常处理
            e.printStackTrace();
        } catch (InvocationTargetException e) {//invoke参数response产生的异常处理
            e.printStackTrace();
        }
    }
}
