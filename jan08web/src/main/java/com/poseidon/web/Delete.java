package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.dao.BoardDAO;
import com.poseidon.util.Util;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 삭제 하기 2024-01-11 + 2024-01-16 로그인 한 사용자 + 내글
		HttpSession session = request.getSession();
		
		if (Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			BoardDAO dao = new BoardDAO();
			// board_no, mid가 같이 있는 클래스는 boardDTO
			BoardDTO dto = new BoardDTO();
			dto.setNo(no);
			dto.setMid((String)session.getAttribute("mid"));
			
			int result = dao.delete(dto);
			
			if (result == 1) {
				//값이 1이면 어디로
				response.sendRedirect("./board");
			} else {
				//값이 0이면 어디로
				response.sendRedirect("./error.jsp");
			}
		} else {
			//숫자가 아니네? -> 에러 표시
			response.sendRedirect("./error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
