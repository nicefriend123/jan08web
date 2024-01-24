package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.db.DBConnection;

//상속할 부모 DAO = DBConn, close 공통적으로 쓸거라 여기서 만들어줌
//db와 close만 갖고있음
public class AbstractDAO  {
	DBConnection db = DBConnection.getInstance(); 
	

	public void logWrite(String ip, String url, String data) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO iplog (iip, iurl, idata) VALUES(?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, url);
			pstmt.setString(3, data);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void close(ResultSet rs, PreparedStatement pstmt, Connection con) { //접근제어자 없음
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
