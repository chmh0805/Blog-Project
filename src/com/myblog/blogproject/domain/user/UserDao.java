package com.myblog.blogproject.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.myblog.blogproject.config.DB;

public class UserDao {
		
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
		
	public UserDao() {
		conn = DB.getConnection();
	}
	
	public int insert(User user) {
		String sql = "INSERT INTO user(username, password, email, role) VALUES(?, ?, ?, 'user')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int findByUsername(String username) {
		String sql = "SELECT id FROM user WHERE username = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt("id");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return -1;
	}
}
