package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.DTO.MemberDTO;
import com.poseidon.dao.MemberDAO;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 잡아주세요
		request.setCharacterEncoding("UTF-8");
		MemberDTO dto = new MemberDTO();
		
		dto.setMid(request.getParameter("id"));
		dto.setMname(request.getParameter("name"));
		dto.setMpw(request.getParameter("pw1"));

		MemberDAO dao = new MemberDAO();
		int result = dao.join(dto);
		//정상적으로 데이터입력을 완료 했다면 로그인 페이지로,
		//비정상이라면 에러로 보내주세요.
		if (result == 1) {
			response.sendRedirect("./login");
		} else {
			response.sendRedirect("./error.jsp");
		}
	}		
}
