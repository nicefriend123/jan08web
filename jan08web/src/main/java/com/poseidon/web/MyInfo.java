package com.poseidon.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.DTO.MemberDTO;
import com.poseidon.dao.MemberDAO;

@WebServlet("/myInfo")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("mid") != null) {
			// mid를 데이터베이스에 질의
			MemberDTO dto = new MemberDTO();
			dto.setMid((String) session.getAttribute("mid"));

			MemberDAO dao = new MemberDAO();
			// DTO에 담아서
			dto = dao.myInfo(dto);

			// myInfo.jsp에 찍어 주도록 해주세요
			request.setAttribute("myInfo", dto);
			//2024-01-23
			List<Map<String, Object>> readData = dao.readData(dto);
			request.setAttribute("readData", readData);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("myInfo.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("./login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// 진짜 수정
		MemberDTO dto = new MemberDTO();
		dto.setMid((String) session.getAttribute("mid"));
		dto.setMpw(request.getParameter("newPW"));

		MemberDAO dao = new MemberDAO();
		int result = dao.changePw(dto);

		if (result == 1) {
			response.sendRedirect("./index.jsp");
		} else {
			response.sendRedirect("./login");
		}
	}
}
