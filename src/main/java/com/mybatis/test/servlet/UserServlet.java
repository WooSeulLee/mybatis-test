package com.mybatis.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybatis.test.service.UserService;
import com.mybatis.test.util.CMDUtil;
import com.mybatis.test.vo.UserVO;
//com.mybatis.test.filter
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService us = new UserService();
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		
		if("user-list".equals(cmd)) {
			UserVO user = new UserVO();
			String uiNumStr = request.getParameter("uiNum");
			if(uiNumStr!=null && !"".equals(uiNumStr)) {
				user.setUiNum(Integer.parseInt(uiNumStr));
			}
			user.setUiId(request.getParameter("uiId"));
			user.setUiName(request.getParameter("uiName"));
			List<UserVO> users = us.getUsers(user);
			request.setAttribute("users", users);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/user-list.jsp");
			rd.forward(request, response);
		}else if("user-view".equals(cmd)) {
			String uiNumStr = request.getParameter("uiNum");
			int uiNum = Integer.parseInt(uiNumStr);
			UserVO user = us.getUser(uiNum);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/user-view.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = CMDUtil.getCmd(request.getRequestURI());
		String msg = "";
		String uri = "";
		UserVO user = new UserVO();
		String uiNumStr = request.getParameter("uiNum");
		if(uiNumStr!=null && !"".equals(uiNumStr)) {
			user.setUiNum(Integer.parseInt(uiNumStr));
		}
		user.setUiBirth(request.getParameter("uiBirth"));
		user.setUiDesc(request.getParameter("uiDesc"));
		user.setUiGender(request.getParameter("uiGender"));
		user.setUiId(request.getParameter("uiId"));
		user.setUiName(request.getParameter("uiName"));
		user.setUiPwd(request.getParameter("uiPwd"));
		
		String[] hobbies  = request.getParameterValues("uiHobby");
		if(hobbies != null && hobbies.length > 0) {
			String hobby="";
			for(String h:hobbies) {
				hobby += h + ",";
			}
			hobby = hobby.substring(0, hobby.length()-1);
			user.setUiHobby(hobby);
		}
		
		if("user-insert".equals(cmd)) {
			msg = "회원등록이 실패하였습니다.";
			uri = "/views/user/user-insert";
			if(us.insertUser(user)==1) {
				msg="회원등록이 성공하였습니다.<br>로그인해주시기 바랍니다.";
				uri="/views/user/login";
			}
		}else if("user-update".equals(cmd)) {
			msg = "회원등록이 실패하였습니다.";
			uri = "/views/user/user-insert";
			if(us.updateUser(user)==1) {
				msg="회원수정이 성공하였습니다.";
				uri="/user/user-view?uiNum=";
			}
		}else if("user-delete".equals(cmd)) {
			msg = "회원삭제가 실패하였습니다.";
			uri = "/user/user-view?uiNum=";
			if(us.updateUser(user)==1) {
				msg="회원삭제가 성공하였습니다.";
				uri="/user/user-list";
			}
		}else if("login".equals(cmd)) {
			msg="아이디비밀번호를 확인해주세요";
			uri = "/views/user/login";
			HttpSession session = request.getSession();
			if("test".equals(user.getUiId())) {
				if("test".equals(user.getUiPwd())) {
					msg = "test님 로그인에 성공하셨습니다.";
					uri = "/";
					session.setAttribute("user", user);
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("uri", uri);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		rd.forward(request, response);
	}

}
