package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.DTO.BoardDTO;
import com.poseidon.DTO.CoffeeDTO;

public class CoffeeDAO extends AbstractDAO {

	public void choice(CoffeeDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO coffe (coname, menu) VALUES(?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getConame());
			pstmt.setString(2, dto.getMenu());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
				
	}

	public List<CoffeeDTO> getShowCoffee() {
		List<CoffeeDTO> list = null;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM coffe";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<CoffeeDTO>();
			while (rs.next()) {
				CoffeeDTO e = new CoffeeDTO();
				e.setConame(rs.getString(1));
				e.setMenu(rs.getString(2));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public void reSet() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM coffe";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}
	
}
