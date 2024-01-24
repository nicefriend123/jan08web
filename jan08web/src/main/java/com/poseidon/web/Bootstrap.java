package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.dao.BoardDAO;

@WebServlet("/bootstrap")
public class Bootstrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bootstrap() {
        super();
    }
    
    
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list =dao.boardList(1);
		
		request.setAttribute("list", list); //list라는 변수로 날아온다 어디서? bootstrap.jsp에서
		
		RequestDispatcher rd = request.getRequestDispatcher("bootstrap.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
