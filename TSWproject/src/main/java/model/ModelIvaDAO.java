package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.IvaBean;
import dao.IvaDAO;

public class ModelIvaDAO implements IvaDAO{
	
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

	@Override
	public IvaBean getIvaByModel() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;
		IvaBean iva = null;
        
		String selectSQL = "SELECT * FROM iva";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				iva = new IvaBean();
				iva.setID(rs.getInt("IDiva"));
				iva.setPercentuale(rs.getDouble("percentuale"));
			}else {
				String insertIVA = "INSERT INTO iva (percentuale) VALUES(?)";   //al primo utilizzo salvo l'iva se non è presente nel db
				ps = connection.prepareStatement(insertIVA);
				ps.setDouble(1,22);
				int result = ps.executeUpdate();
				System.out.print(result);
				iva = new IvaBean();
				iva.setID(1);
				iva.setPercentuale(22);
			}
				
		}finally {
			try {
				if (preparedStatement != null || ps!=null)
					ps.close();
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return iva;
	}

	@Override
	public boolean changeIva(IvaBean iva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0; 
		String selectSQL = "UPDATE iva SET percentuale = ? WHERE IDiva = ?";

		try {
		        connection = ds.getConnection();
		        System.out.println(iva.getID()+" "+iva.getPercentuale());
			    preparedStatement = connection.prepareStatement(selectSQL);
		     	preparedStatement.setDouble(1, iva.getPercentuale());
			    preparedStatement.setInt(2, iva.getID());
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


}
