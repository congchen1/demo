package com.chen.web.onelogin.servlet;;

import com.chen.web.onelogin.vo.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
			throws ServletException,IOException{

		rsp.setContentType("text/html;charset=utf-8");
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");

		PrintWriter out = rsp.getWriter();
		HttpSession session = req.getSession();

		if(uid == null || name == null || uid.equals("") || name.equals("")){
			out.print("锟皆诧拷锟斤拷,锟斤拷牟锟斤拷锟斤拷锟轿拷锟?");
			return;
		}

		User user = new User();
		user.setName(name);
		user.setUid(uid);
		//锟斤拷锟斤拷时锟斤拷
		user.setTime(new Date().getTime());
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟?
		user.setSerial((new Date().getTime())+""+(new Random(100).nextInt(50)));
		session.setAttribute("user", user);
		rsp.sendRedirect("static/admin/success.jsp");

	}

	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
			throws ServletException,IOException{

		doGet(req,rsp);
	}
}