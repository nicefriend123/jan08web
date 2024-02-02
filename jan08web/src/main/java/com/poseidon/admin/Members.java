package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.DTO.MemberDTO;
import com.poseidon.dao.AdminDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Members() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("grade");
		
		AdminDAO dao = new AdminDAO();
		List<MemberDTO> list = null;
		if (request.getParameter("grade") == null || request.getParameter("grade").equals("")) {
			list = dao.memberList();
		} else {
			list = dao.memberList(Util.str2Int(request.getParameter("grade")));
		}
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("mno"));
		System.out.println(request.getParameter("grade"));
		//db에 변경
		MemberDTO dto = new MemberDTO();
		dto.setMno(Util.str2Int(request.getParameter("mno")));
		dto.setMgrade(Util.str2Int(request.getParameter("grade")) );
		MemberDAO dao = new MemberDAO();
		dao.changeGrade(dto);
		
		//페이지 이동
		if (request.getParameter("currentgrade") == null || request.getParameter("currentgrade").equals("")) {
			response.sendRedirect("./members");
		} else {
			response.sendRedirect("./members?grade="+ request.getParameter("currentgrade"));
		}
	}
}
