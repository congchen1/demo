package com.chen.web.onelogin.servlet;

import com.chen.web.onelogin.vo.UList;
import com.chen.web.onelogin.vo.User;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Check implements Filter {

	ServletContext application;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse rsp,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		HttpServletResponse response = (HttpServletResponse)rsp;
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}

		//为了能看到明显的效果，默认为5秒查询一次用户是否重复登陆
		if(((new Date().getTime()) - user.getTime()) > 5000){

			//获取用户列表
			Map ulist = (Map)application.getAttribute("ulist");
			if(ulist != null){
				UList ul = (UList)ulist.get(user.getUid());
				if(ul == null){
					response.sendRedirect(request.getContextPath()+"/static/index.jsp");
					return;
				}
				//表明用户多次登陆了，提示用户下线
				if(!user.getSerial().equals(ul.getSerial())){

					request.getRequestDispatcher("/static/admin/error.jsp").forward(request, response);
					return;
				}
			}//end inner if
			//修改用户此次验证的时间
			user.setTime(new Date().getTime());
		}//end outer if

		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {

		application = config.getServletContext();

	}

}
