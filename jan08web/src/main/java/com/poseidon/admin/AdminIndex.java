package com.poseidon.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/index") //url의 경로 = 실제 파일과 다르게 호출할 url을 지정합니다
//adminIndex -> admin/index로 변경 웹서버 호출되는데로 웹앱의폴더랑 서블릿을 새롭게
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); 정보 보여주는 녀석
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin.jsp");//앞에.빠짐 폴더라서,파일있는경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response); doget한테 다시 일을시킴
	}

}
