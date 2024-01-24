package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.DTO.CommentDTO;
import com.poseidon.dao.CommentDAO;
import com.poseidon.util.Util;

@WebServlet("/commentEdit")
public class CommentEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentEdit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int result = 0;	
		
		if (session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("no")) ) {
			CommentDTO dto = new CommentDTO();
			dto.setCno(Util.str2Int2(request.getParameter("no")));
			dto.setComment(request.getParameter("comment"));
			
			CommentDAO dao = new CommentDAO();
			result = dao.commentEdit(dto);
		}
		
		PrintWriter pw = response.getWriter(); 
		pw.print(result);
	}
}
