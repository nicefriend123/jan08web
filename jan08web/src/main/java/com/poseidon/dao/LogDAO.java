package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.DTO.LogDTO;
import com.poseidon.db.DBConnection;

public class LogDAO extends AbstractDAO {
	public void whoIP(LogDTO dto) {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO log (ip) "
				+ "VALUES (?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getIp());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
	}
}
