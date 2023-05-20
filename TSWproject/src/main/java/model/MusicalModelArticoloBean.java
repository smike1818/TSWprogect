package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ArticoloBean;
import bean.CategoriaBean;
import dao.ArticoloDAO;
import dao.CategoriaDAO;

/*
 * Implementazione di ArticoloDAO
 * effettua la connessione automatica al database sfruttando i servizi offerti da Tomcat
 */

public class MusicalModelArticoloBean implements ArticoloDAO{

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

	private static final String TABLE_NAME = "articolo";
	
	@Override
	public void doSave(ArticoloBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
		String insertSQL = "INSERT INTO " + MusicalModelArticoloBean.TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getID());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setInt(4, product.getQuantita());
			preparedStatement.setString(5, product.getColore());
			preparedStatement.setString(6, product.getDescrizione());
			preparedStatement.setString(7, product.getMarca());
			preparedStatement.setInt(8, product.getTipo());
			preparedStatement.setInt(9, product.getCorde());
			preparedStatement.setString(10, product.getTipologia());
			preparedStatement.setInt(11, product.getCategoria().getID());

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
	public boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + MusicalModelArticoloBean.TABLE_NAME + " WHERE codice = ?";

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
	public synchronized List<ArticoloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean cat = null;
		CategoriaDAO model = new ModelCategoriaDAO();

		List<ArticoloBean> products = new LinkedList<ArticoloBean>();
		
		String selectSQL = "SELECT * FROM " + MusicalModelArticoloBean.TABLE_NAME ;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ArticoloBean ab = new ArticoloBean();
								
				ab.setID(rs.getInt("codice"));
		    	ab.setColore(rs.getString("colore"));
		    	ab.setNome(rs.getString("nome"));
		    	ab.setTipologia(rs.getString("tipologia"));
		    	ab.setDescrizione(rs.getString("descrizione"));
		    	ab.setMarca(rs.getString("marca"));
		    	ab.setQuantita(rs.getInt("quantita"));
		    	ab.setPrezzo(rs.getDouble("prezzoBase"));
		    	ab.setTipo(rs.getInt("tipo"));
		    	ab.setCorde(rs.getInt("corde"));
		    	
		    	cat = model.doRetrieveByKey(rs.getInt("categoria"));
		    	
		    	ab.setCategoria(cat);	
		    	products.add(ab);
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
		return products;
	}

	@Override
	public ArticoloBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM " + MusicalModelArticoloBean.TABLE_NAME + " join categoria WHERE codice = ?";
		ArticoloBean ab = null;
		CategoriaBean cat = null;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ab = new ArticoloBean();
				cat = new CategoriaBean();
				
				ab.setID(rs.getInt("codice"));
		    	ab.setColore(rs.getString("colore"));
		    	ab.setNome(rs.getString("nome"));
		    	ab.setTipologia(rs.getString("tipologia"));
		    	ab.setDescrizione(rs.getString("descrizione"));
		    	ab.setMarca(rs.getString("marca"));
		    	ab.setQuantita(rs.getInt("quantita"));
		    	ab.setPrezzo(rs.getDouble("prezzoBase"));
		    	ab.setTipo(rs.getInt("tipo"));
		    	ab.setCorde(rs.getInt("corde"));
		    	
		    	cat.setID(rs.getInt("IDcat"));
		    	cat.setNome(rs.getString("nome_cat"));
		    	
		    	ab.setCategoria(cat);	

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
		return ab;
	}
	
	@Override
	public void doChangeQuantity(int id, float q) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "UPDATE "+ MusicalModelArticoloBean.TABLE_NAME + " SET quantita = ? WHERE codice = ?";

		try {
		        connection = ds.getConnection();
			    preparedStatement = connection.prepareStatement(selectSQL);
		     	preparedStatement.setFloat(1, q);
			    preparedStatement.setInt(2, id);
			    preparedStatement.executeUpdate();
		} finally {
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
	public String getFirstImage(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String ImageName = null;
		
		String selectSQL = "SELECT * FROM " + MusicalModelArticoloBean.TABLE_NAME + " a join image i WHERE a.codice = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				ImageName = rs.getString("i.nome");
			}else
				ImageName = "default.jpg";

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return ImageName;
	}

}
