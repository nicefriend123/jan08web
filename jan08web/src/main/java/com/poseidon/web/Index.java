package com.poseidon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.DTO.LogDTO;
import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dao.LogDAO1;
import com.poseidon.util.Util;

@WebServlet("/index")
public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Index() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LogDAO1 log = new LogDAO1();
        log.logWrite(Util.getIP(request), "./index", null);
        // 클라이언트의 IP 주소를 가져오는 메서드 호출
        String clientIP = getClientIP(request);

        // 가져온 IP 주소를 콘솔에 출력하거나 다른 작업 수행
        System.out.println("Client IP: " + clientIP);

        // 클라이언트 IP를 request 속성에 저장
        request.setAttribute("clientIP", clientIP);
        // 데이터베이스에 저장
        LogDTO dto = new LogDTO();
        dto.setIp(clientIP);
        LogDAO dao = new LogDAO();
        dao.whoIP(dto);
        // JSP 페이지로 포워딩
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // 클라이언트의 IP 주소를 가져오는 메서드
    private String getClientIP(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");

        if (clientIP == null || clientIP.length() == 0 || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }

        if (clientIP == null || clientIP.length() == 0 || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }

        if (clientIP == null || clientIP.length() == 0 || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }

        return clientIP;
    }
    
}
