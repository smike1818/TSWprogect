package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;
import dao.UserDAO;

public class ModelUserDAO implements UserDAO{
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/musicalstoredb");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "utente";
	@Override
	public void doSave(UserBean user) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;
        
		String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE username = ? OR email = ?";
		String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(ifExists);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			if(!ps.executeQuery().next()) {
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, user.getNome());
				preparedStatement.setString(2, user.getCognome());
				preparedStatement.setString(3, user.getEmail());
				preparedStatement.setString(4, user.getPassword());
				preparedStatement.setString(5, user.getCF());
				preparedStatement.setString(6, user.getUsername());
				preparedStatement.setInt(7, 1);
				preparedStatement.setString(8, "none");
				preparedStatement.executeUpdate();
			}
			else
				throw new SQLException();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void doRetrieveByUsr(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE username = ? AND pw = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) { 
			    user.setCF(rs.getString("CF"));
			    user.setNome(rs.getString("nome"));
			    user.setCognome(rs.getString("cognome"));
			    user.setCognome(rs.getString("email"));
				preparedStatement.close();
		}else 
			throw new SQLException();
	  }finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
	}
	
}
