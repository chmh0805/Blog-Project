package com.myblog.blogproject.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myblog.blogproject.domain.user.User;
import com.myblog.blogproject.service.UserService;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		RequestDispatcher dis;
		
		if (cmd.equals("joinForm")) {
			dis = request.getRequestDispatcher("/user/join.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			
			int result = userService.회원가입(user);
			
			if (result == 1) {
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				
			}
			
		} else if (cmd.equals("usernameCheck")) {
			BufferedReader br = request.getReader();
			String username = br.readLine();
			PrintWriter out = response.getWriter();
			
			int result = userService.아이디중복체크(username);
			
			if (result == -1) {
				out.print("ok");
				out.flush();
			} else {
				out.print("fail");
				out.flush();
			}
			
		}
	}

}
