package com.mybatis.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.test.service.CarService;
import com.mybatis.test.util.CMDUtil;
import com.mybatis.test.vo.CarVO;

public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarService cs = new CarService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = CMDUtil.getCmd(uri);
		
		if("car-list".equals(cmd)) {
			CarVO car = new CarVO();
			String ciNumStr = request.getParameter("ciNum");
			List<CarVO> cars = cs.getCars(car);
			request.setAttribute("cars", cars);
		}else if("car-view".equals(cmd) || "car-update".equals(cmd)) {
			String ciNumStr = request.getParameter("ciNum");
			int ciNum = Integer.parseInt(ciNumStr);
			CarVO car = cs.getCar(ciNum);
			request.setAttribute("car", car);
		} 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views" + uri + ".jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = CMDUtil.getCmd(request.getRequestURI());
		CarVO car = new CarVO();
		
		String ciNumStr = request.getParameter("ciNum");
		if(ciNumStr!=null && !"".equals(ciNumStr)) {
			car.setCiNum(Integer.parseInt(ciNumStr));
		}
		car.setCiName(request.getParameter("ciName"));
		car.setCiYear(request.getParameter("ciYear"));
		String msg = "";
		String uri = "";
		if("car-insert".equals(cmd)) {
			msg = "등록이 실패하였습니다.";
			uri = "/views/car/car-insert";
			int result = cs.insertCar(car);
			if(result==1) {
				msg = "등록이 성공하였습니다.";
				uri = "/car/car-list";
			}
		}else if("car-update".equals(cmd)) {
			msg = "수정이 실패하였습니다.";
			uri = "/car/car-update?ciNum=" + ciNumStr;
			int result = cs.updateCar(car);
			if(result==1) {
				msg = "수정이 성공하였습니다.";
				uri = "/car/car-view?ciNum=" + ciNumStr;
			}
		}else if("car-delete".equals(cmd)) {
			msg = "삭제가 실패하였습니다.";
			uri = "/car/car-update?ciNum=" + ciNumStr;
			int result = cs.deleteCar(car);
			if(result==1) {
				msg = "삭제가 성공하였습니다.";
				uri = "/car/car-list";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("uri", uri);
		RequestDispatcher rd  =request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		rd.forward(request, response);
	}

}
