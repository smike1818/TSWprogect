package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AcquistoBean;
import bean.ContoBean;
import bean.UserBean;
import dao.AcquistoDAO;
import dao.ContoDAO;
import dao.UserDAO;

public class ModelAcquistoDAO implements AcquistoDAO {

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
	
	private static final String TABLE_NAME = "acquisto";
	
	@Override
	public void doSave(AcquistoBean acq) throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				PreparedStatement ps = null;
		        
				String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE consumer = ? AND conto = ?";
				String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?)";

				try {
					connection = ds.getConnection();
					ps = connection.prepareStatement(ifExists);
					ps.setString(1, acq.getConsumer().getCF());
					ps.setString(2, acq.getConto().getIBAN());
					if(!ps.executeQuery().next()) {
						preparedStatement = connection.prepareStatement(insertSQL);
						preparedStatement.setString(1, acq.getConsumer().getCF());
						preparedStatement.setString(2, acq.getConto().getIBAN());
						preparedStatement.setDate(3, (Date) acq.getDate());
						preparedStatement.setDouble(4, acq.getImporto());
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
	public Collection<AcquistoBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcquistoBean doRetrieveByKey(String cliente, String conto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AcquistoBean acq = null;
		ContoBean cont = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		ContoDAO modelc = new ModelContoDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " where consumer = ? AND conto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cliente);
			preparedStatement.setString(2, conto);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				acq = new AcquistoBean();
		    	
		    	acq.setImporto(rs.getDouble("importo"));
		    	in = model.doRetrieveByKey(rs.getString("consumer"));
		    	cont = modelc.doRetrieveByKey(rs.getString("conto"));
		    	
		    	acq.setConsumer(in);
		    	acq.setConto(cont);
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
		return acq;
	}
	
	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AcquistoBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
