package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.TelefonoBean;
import bean.UserBean;
import dao.TelefonoDAO;

public class ModelTelefonoDAO implements TelefonoDAO {
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/musicalstore");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "telefono";

	@Override
	public void doSave(TelefonoBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
		String insertSQL = "INSERT INTO " + TABLE_NAME +" (numero,prefisso,utente) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNumero());
			preparedStatement.setString(2, product.getPrefisso());			
			preparedStatement.setString(3, product.getUtente().getCF());			

			preparedStatement.executeUpdate();

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
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE numero = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

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
	public TelefonoBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TelefonoBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TelefonoBean> doRetrieveAllPhones(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		

		List<TelefonoBean> phones = new LinkedList<TelefonoBean>();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE utente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user.getCF());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TelefonoBean phone = new TelefonoBean();
								
				phone.setPrefisso(rs.getString("prefisso"));
				phone.setNumero(rs.getString("numero"));
				phone.setIsPrimary(rs.getBoolean("isPrimary"));
				phone.setUtente(user);
				
				phones.add(phone);			
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return phones;
	}

	@Override
	public boolean doPrefer(String numero, UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;

		int result = 0;
		String updateToFalse = "UPDATE "+ TABLE_NAME + " SET isPrimary = ? WHERE isPrimary = ? AND utente = ?";
		String updateSQL = "UPDATE " + TABLE_NAME + " SET isPrimary = ? WHERE numero = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(updateToFalse);
			ps.setBoolean(1, false);
			ps.setBoolean(2, true);
			ps.setString(3, user.getCF());
			
			if(ps.executeUpdate()>=0) {
			
			  preparedStatement = connection.prepareStatement(updateSQL);
			  preparedStatement.setBoolean(1, true);
			  preparedStatement.setString(2,numero);

			  result = preparedStatement.executeUpdate();
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();				 
			}finally {
				 try{
					 if(ps!=null)    
						ps.close();
				 }finally {
				   if (connection != null)
					  connection.close();
				 }
			}
		}
		return (result != 0);
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPhone(String data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE numero = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, data);
		
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) { 
			throw new SQLException();
		}			   
	  }finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
	  
	  return true;
	}

}
