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

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//오는 값 받기
		//System.out.println(commentcontent + " : " + bno);
		String commentcontent = request.getParameter("commentcontent");//댓글내용 + 특수기호 제거
		
		//오는 값에서 특수기호 <,>, 이 두개를 특수기호로 변경하기
		commentcontent = Util.removeTag(commentcontent);
		
		//줄바꿈처리해주기 - util에 쓰기 제목, 본문내용제외 /r /n /nr
		commentcontent = Util.addBR(commentcontent);
	
		String bno = request.getParameter("bno");//글번호
		if (session.getAttribute("mid") != null && commentcontent != null && bno != null ) {
			CommentDTO dto = new CommentDTO();
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str2Int(bno));
			dto.setMid((String)session.getAttribute("mid"));
			//이동해주세요.
			//저장해주세요
			dto.setIp(Util.getIP(request));
			CommentDAO dao = new CommentDAO();
			int result = dao.comment(dto);
			
			if (result == 1) {
				response.sendRedirect("./detail?no="+bno);
//				System.out.println("처리 결과 :" + result);
			} else {
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./error.jsp");
		}
	}
}
