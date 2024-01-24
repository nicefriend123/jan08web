package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poseidon.DTO.CommentDTO;

public class CommentDAO extends AbstractDAO {

	public int comment(CommentDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO comment (ccomment, board_no, mno, cip) " 
				+ "VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int commentDelete(CommentDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt =null;
		String sql = "UPDATE comment SET cdel=0 "
				+ "WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int commentEdit(CommentDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET ccomment=? WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid =?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getCno());
			pstmt.setString(3, dto.getMid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}