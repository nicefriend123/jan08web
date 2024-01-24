package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO1;
import com.poseidon.util.Util;


@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BoardList() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//DAO랑 연결
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Util.str2Int2(request.getParameter("page"));
		}
		
		//log
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("url", "./login");
		log.put("data", "page="+page);
		LogDAO1 logDAO = new LogDAO1();
		logDAO.write(log);
		
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.boardList(page);
		int totalCount = dao.totalCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
		rd.forward(request, response);
		//dispatcher forward url은 그대로인데 보이는 화면만 바꿔줌
		//send...는 url바꿔줌
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
