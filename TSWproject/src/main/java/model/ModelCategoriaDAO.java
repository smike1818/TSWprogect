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

import bean.CategoriaBean;
import dao.CategoriaDAO;

public class ModelCategoriaDAO implements CategoriaDAO{

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
	
	private static final String TABLE_NAME = "categoria";
	
	@Override
	public void doSave(CategoriaBean cat) throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				PreparedStatement ps = null;
		        
				String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE IDcat = ? OR nome_cat = ?";
				String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?)";

				try {
					connection = ds.getConnection();
					ps = connection.prepareStatement(ifExists);
					ps.setInt(1, cat.getID());
					ps.setString(2, cat.getNome());
					if(!ps.executeQuery().next()) {
						preparedStatement = connection.prepareStatement(insertSQL);
						preparedStatement.setInt(1, cat.getID());
						preparedStatement.setString(2, cat.getNome());
						preparedStatement.setString(3, cat.getDescrizione());
						preparedStatement.setBoolean(4, cat.getTipo());
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IDcat = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	public CategoriaBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CategoriaBean cat = null;
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " where IDcat = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				 cat = new CategoriaBean();
		    	
		    	cat.setID(rs.getInt("IDcat"));
		    	cat.setNome(rs.getString("nome_cat"));
		    	cat.setDescrizione(rs.getString("descrizione"));
		    	cat.setTipo(rs.getBoolean("tipo"));
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
		return cat;
	}

	@Override
	public Collection<CategoriaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<CategoriaBean> categories = new LinkedList<CategoriaBean>();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CategoriaBean cat = new CategoriaBean();
		    	
		    	cat.setID(rs.getInt("IDcat"));
		    	cat.setNome(rs.getString("nome_cat"));
		    	cat.setDescrizione(rs.getString("descrizione"));
		    	cat.setTipo(rs.getBoolean("tipo"));
		    	
		    	categories.add(cat);		    	    	
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
		return categories;
	}

}
