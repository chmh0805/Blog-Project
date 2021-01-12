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
import javax.servlet.http.HttpSession;

import com.myblog.blogproject.domain.user.User;
import com.myblog.blogproject.domain.user.dto.LoginReqDto;
import com.myblog.blogproject.domain.user.dto.LoginRespDto;
import com.myblog.blogproject.service.UserService;
import com.myblog.blogproject.util.Script;

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
				dis = request.getRequestDispatcher("/user/login.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원가입 실패");
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
			
		} else if (cmd.equals("loginForm")) {
			dis = request.getRequestDispatcher("/user/login.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			
			LoginRespDto respDto = userService.로그인(dto);
			
			if (respDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", respDto);
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			}
			
		} else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			dis = request.getRequestDispatcher("/index.jsp");
			dis.forward(request, response);
		}
	}

}
