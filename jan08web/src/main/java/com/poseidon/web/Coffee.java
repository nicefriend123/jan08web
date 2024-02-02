package com.poseidon.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.poseidon.DTO.CoffeeDTO;
import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.CoffeeDAO;

@WebServlet("/coffee")
public class Coffee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Coffee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoffeeDAO dao = new CoffeeDAO();
		
		List<CoffeeDTO> coffeeList = dao.getShowCoffee();
		request.setAttribute("coffeeList", coffeeList);
		
		RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CoffeeDAO dao = new CoffeeDAO();
		CoffeeDTO dto = new CoffeeDTO();
		List<CoffeeDTO> coffeeList = dao.getShowCoffee();
		if (coffeeList.size() > 25) {
			dao.reSet();
		} else {
			request.setAttribute("coffeeList", coffeeList);
			dto.setConame(request.getParameter("name"));
			dto.setMenu(request.getParameter("menu"));
			dao.choice(dto);
		}
		
		response.sendRedirect(request.getContextPath() + "/coffee");
	}
}
