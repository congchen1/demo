package com.chen.web.onelogin.listener;

import com.chen.web.onelogin.vo.UList;
import com.chen.web.onelogin.vo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 单点登录session监听
 */
public class UserListener implements HttpSessionAttributeListener,HttpSessionListener,
        ServletContextListener {
    ServletContext application = null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化用户列表
        Map<String,UList> ulist = new HashMap<>();
        application = servletContextEvent.getServletContext();
        application.setAttribute("ulist",ulist);
    }

    //服务器关闭时清空内容
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Map<String,UList> ulist = (Map<String,UList>)servletContextEvent
                .getServletContext().getAttribute("ulist");
        if(ulist != null){
            ulist.clear();
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        Object obj = httpSessionBindingEvent.getValue();
        if(obj.getClass().getName().equals("com.chen.web.onelogin.vo.User")){
            User user = (User)obj;
            //建立用户列表实体
            UList ul = new UList();
            ul.setUid(user.getUid());
            ul.setName(user.getName());
            ul.setSerial(user.getSerial());
            //如果用户已经登陆他的信息将会被map映射替代，因为KEY是 唯一的UID
            Map<String,UList> map = (Map<String,UList>)application.getAttribute("ulist");
            map.put(ul.getUid(),ul);
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        Object obj = httpSessionBindingEvent.getValue();
        if(obj.getClass().getName().equals("com.chen.web.onelogin.vo.User")){
            User user = (User)obj;
            //该用户的信息消除
            Map<String,UList> map = (Map<String,UList>)application.getAttribute("ulist");
            map.remove(user.getUid());
        }
    }

    //用户重新登陆
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        Object obj = httpSessionBindingEvent.getValue();
        if(obj.getClass().getName().equals("com.chen.web.onelogin.vo.User")){
            User user = (User)httpSessionBindingEvent.getSession().getAttribute("user");
            //建立用户列表实体
            UList ul = new UList();
            ul.setSerial(user.getSerial());
            ul.setUid(user.getUid());
            ul.setName(user.getName());
            //用户原来的信息将会被map映射替代，因为KEY是唯一的UID
            Map<String,UList> ulist = (Map<String,UList>)application.getAttribute("ulist");
            ulist.put(user.getUid(), ul);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }
    //把用户过期或者注销把用户信息删除
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null){
            System.out.println(user.getSerial());
            Map<String,UList> map = (Map<String,UList>)application.getAttribute("ulist");
            //以登录名为key获取在线用户信息
            UList uList = map.get(user.getUid());
            if(uList != null && uList.getSerial().equals(user.getSerial())){
                map.remove(user.getUid());
            }
        }

    }
}
