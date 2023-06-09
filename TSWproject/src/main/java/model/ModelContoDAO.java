package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ContoBean;
import bean.UserBean;
import dao.ContoDAO;
import dao.UserDAO;

public class ModelContoDAO implements ContoDAO{

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
	
	private static final String TABLE_NAME = "conto";
	
	@Override
	public void doSave(ContoBean conto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;
        
		String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE IBAN = ?";
		String insertSQL = "INSERT INTO " + TABLE_NAME +" (IBAN,intestatario,numero_carta,cvv) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(ifExists);
			ps.setString(1, conto.getIBAN());
			if(!ps.executeQuery().next()) {
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, conto.getIBAN());
				preparedStatement.setString(2, conto.getIntestatario().getCF());
				preparedStatement.setString(3, conto.getNumCarta());
				preparedStatement.setString(4, conto.getCvv());
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
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IBAN = ?";

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
	public ContoBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ContoBean conto = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " where IBAN = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				conto = new ContoBean();
		    	
		    	conto.setIBAN(rs.getString("IBAN"));
		    	conto.setNumCarta(rs.getString("numero_carta"));
		    	conto.setCvv(rs.getString("cvv"));	
		    	conto.setIsPrimary(rs.getBoolean("isPrimary"));
		    	
		    	in = model.doRetrieveByKey(rs.getString("intestatario"));
		    	conto.setIntestatario(in);
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
		return conto;
	}

	@Override
	public Collection<ContoBean> doRetrieveAll(String order) throws SQLException {
		return null;
	}
	public Collection<ContoBean> doRetrieveByUsr(String user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        List<ContoBean>cards = new LinkedList<ContoBean>();
		ContoBean conto = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + "WHERE intestatario = ? ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				conto = new ContoBean();
		    	
		    	conto.setIBAN(rs.getString("IBAN"));
		    	conto.setNumCarta(rs.getString("numero_carta"));
		    	conto.setCvv(rs.getString("cvv"));	
		    	conto.setIsPrimary(rs.getBoolean("isPrimary"));
		    	
		    	in = model.doRetrieveByKey(rs.getString("intestatario"));
		    	conto.setIntestatario(in);
		    	
		    	cards.add(conto);
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
		return cards;
	}
	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ContoBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ContoBean> doRetrieveByUsr(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ContoBean conto = null;
		List<ContoBean>conti = new ArrayList<ContoBean>();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " where intestatario = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user.getCF());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				conto = new ContoBean();
		    	
		    	conto.setIBAN(rs.getString("IBAN"));
		    	conto.setNumCarta(rs.getString("numero_carta"));
		    	conto.setCvv(rs.getString("cvv"));
		    	conto.setIsPrimary(rs.getBoolean("isPrimary"));
		    	conto.setIntestatario(user);
		    	
		    	conti.add(conto);
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
		return conti;
	}

	@Override
	public boolean doPrefer(String iban, UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;

		int result = 0;
		String updateToFalse = "UPDATE "+ TABLE_NAME + " SET isPrimary = ? WHERE isPrimary = ? AND intestatario = ?";
		String updateSQL = "UPDATE " + TABLE_NAME + " SET isPrimary = ? WHERE IBAN = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(updateToFalse);
			ps.setBoolean(1, false);
			ps.setBoolean(2, true);
			ps.setString(3, user.getCF());
			
			if(ps.executeUpdate()>=0) {
			
			  preparedStatement = connection.prepareStatement(updateSQL);
			  preparedStatement.setBoolean(1, true);
			  preparedStatement.setString(2,iban);

			  result = preparedStatement.executeUpdate();
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
		return (result != 0);
	}

	@Override
	public Object doRetrieveAll(String order, String cf) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        List<ContoBean>cards = new LinkedList<ContoBean>();
		ContoBean conto = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE intestatario = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,cf);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				conto = new ContoBean();
		    	
		    	conto.setIBAN(rs.getString("IBAN"));
		    	conto.setNumCarta(rs.getString("numero_carta"));
		    	conto.setCvv(rs.getString("cvv"));	
		    	conto.setIsPrimary(rs.getBoolean("isPrimary"));
		    	
		    	in = model.doRetrieveByKey(rs.getString("intestatario"));
		    	conto.setIntestatario(in);
		    	
		    	cards.add(conto);
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
		return cards;
	}

	@Override
	public boolean checkNum(String data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        
		  try {
			String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE numero_carta = ?";
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

	@Override
	public boolean checkIban(String data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE IBAN = ?";
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

