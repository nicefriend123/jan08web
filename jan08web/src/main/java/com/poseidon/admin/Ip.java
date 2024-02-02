package com.poseidon.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;

@WebServlet("/admin/ip") //url의 경로 = 실제 파일과 다르게 호출할 url을 지정합니다.
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("ip"));
		
		AdminDAO dao = new AdminDAO();
		List<Map<String, Object>> list = null;
		//List<Map<String, Object>> list = dao.ipList(); 
		//List<Map<String, Object>> distinctList = dao.ipdistinctList(); 
		//List<Map<String, Object>> manyConnect = dao.ipmanyConnect(); 
		request.setAttribute("distinctList", dao.ipdistinctList());
		request.setAttribute("manyConnect", dao.ipmanyConnect());
		
		if (request.getParameter("ip") != null && !request.getParameter("ip").equals("")) {
			list = dao.ipList(request.getParameter("ip"));
		} else {
			list = dao.ipList();
		}
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
