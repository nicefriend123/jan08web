package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO1;
import com.poseidon.DTO.*;
import com.poseidon.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 오는 no 잡기
      // int no = Integer.parseInt(request.getParameter("no"));
      int no = Util.str2Int(request.getParameter("no"));

      BoardDAO dao = new BoardDAO();
      //log
      //LogDAO1 log = new LogDAO1();
      dao.logWrite(Util.getIP(request), "./detail", "no="+no);
      
      // 데이터베이스에 질의하기
      //
      //로그인한 회원이라면 읽음수 올리기 2024-01-19 프레임워크 프로그래밍(1.31)
      HttpSession session = request.getSession();
      if (session.getAttribute("mid") != null) {
    	  //bno,mno
    	  dao.countUp(no, (String)session.getAttribute("mid"));
	}
      
      BoardDTO dto = dao.detail(no);
      
      //System.out.println(dto.getTitle());
      //System.out.println(dto.getContent() == null);
      
      if(no == 0 || dto.getContent() == null) {
         // null이면 에러로>
         response.sendRedirect("./error.jsp");
      }else {
         // 정상 출력>
         // 내용 가져오기
         request.setAttribute("detail", dto); //글하나 나가는 녀석
         //댓글이 있다면 List 뽑아내기
         List<CommentDTO> commentList = dao.commentList(no);
         
         if (commentList.size() > 0) { //댓글이 있을때만 붙이기
        	 request.setAttribute("commentList", commentList);
         }
         
        	 // 리퀘스트 디스패처 호출하기
        	 RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
        	 rd.forward(request, response);
      
	}
      
      // 내용 가져오기
      //request.setAttribute("detail", dto);
      
      // 리퀘스트 디스패처 호출하기
      //RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
      //rd.forward(request, response);
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}