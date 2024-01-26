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

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//System.out.println(request.getParameter("no"));
		//System.out.println(request.getParameter("cno"));
		
		if (session.getAttribute("mid") != null 
				&& Util.intCheck(request.getParameter("no")) //board_no값 정수형 맞아?
				&& Util.intCheck2(request.getParameter("cno")) ) {
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str2Int2(request.getParameter("cno")));
			//dto.setBoard_no(Util.str2Int2(request.getParameter("no")));
			
			CommentDAO dao = new CommentDAO();
			int result = dao.commentDelete(dto);
			if (result == 1) {
				response.sendRedirect("./detail?no="+request.getParameter("no"));
			} else {
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int result = 0;
		
		if (session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("no")) ) {
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str2Int2(request.getParameter("no")));
			
			CommentDAO dao = new CommentDAO();
			result = dao.commentDelete(dto);
		}
		
		PrintWriter pw = response.getWriter(); 
		pw.print(result);
	}
}
