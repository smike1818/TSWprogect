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
import bean.IndirizzoBean;
import bean.UserBean;
import dao.IndirizzoDAO;
import dao.UserDAO;

public class ModelIndirizzoDAO implements IndirizzoDAO{

	private static DataSource ds;
	private UserDAO usermodel = new ModelUserDAO();

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/musicalstore");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "indirizzo";
	
	@Override
	public void doSave(IndirizzoBean ind) throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				PreparedStatement ps = null;
		        
				String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE via = ? AND civico = ? AND citta = ?";
				String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?)";

				try {
					connection = ds.getConnection();
					ps = connection.prepareStatement(ifExists);
					ps.setString(1, ind.getVia());
					ps.setInt(2, ind.getCivico());
					ps.setString(3, ind.getCitta());
					if(!ps.executeQuery().next()) {
						preparedStatement = connection.prepareStatement(insertSQL);
						preparedStatement.setString(1, ind.getVia());
						preparedStatement.setInt(2, ind.getCivico());
						preparedStatement.setString(3, ind.getCitta());
						preparedStatement.setInt(4, ind.getCap());
						preparedStatement.setString(5, ind.getCliente().getCF());
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
	public IndirizzoBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doDelete(String CF) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE cliente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, CF);
			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public IndirizzoBean doRetrieveByKey(String CF) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		IndirizzoBean ind = new IndirizzoBean();
		UserBean user = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE cliente = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, CF);
		
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) { 
			    ind.setCap(rs.getInt("cap"));
			    ind.setCitta(rs.getString("citta"));
			    ind.setCivico(rs.getInt("civico"));
			    ind.setVia(rs.getString("via"));
			    
			    user = usermodel.doRetrieveByKey(CF);
			    
			    ind.setCliente(user);
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
	  
	  return ind;
	}
	
	

}