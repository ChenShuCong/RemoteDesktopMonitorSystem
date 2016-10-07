package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class AdminDao {
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	//�������Ա���û���������
	public boolean checkAdmin(String username, String password) {
		boolean b = false;
		try {
			//sql��ѯ���
			String sql = "select * from admin where username=? and password=?";
			
			//�������ݿ�����
			conn = DB.getConn();
			
			//����PreparedStatement
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			//��ò�ѯ�����
			rs = ps.executeQuery();
			
			//�����Ƿ�����û�
			if(rs.next()) {
				b = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, ps, rs);
		}
		return b;
	}
	
	
	//���Ĺ���Ա����
	public boolean updatePassword(String password) {
		boolean b = false;
		try {
			conn = DB.getConn();
			
			String sql = "update admin set password = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);

			if(ps.executeUpdate() != 0) {
				b =true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, ps, rs);
		}
		return b;
 	}
	
	//ע���û�
	public boolean register(String username, String password) {
		boolean b = false;
		
		try {
			conn = DB.getConn();
			String sql = "insert into user (username, password) values (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			if(ps.executeUpdate() != 0) {
				b = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, ps, rs);
		}
		
		return b;
	}
	
	
	
	
	
}
