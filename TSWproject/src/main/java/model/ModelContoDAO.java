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
		String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?)";

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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        List<ContoBean>cards = new LinkedList<ContoBean>();
		ContoBean conto = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				conto = new ContoBean();
		    	
		    	conto.setIBAN(rs.getString("IBAN"));
		    	conto.setNumCarta(rs.getString("numero_carta"));
		    	conto.setCvv(rs.getString("cvv"));	
		    	
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

}
