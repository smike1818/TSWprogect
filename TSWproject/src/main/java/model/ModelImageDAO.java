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
import bean.ArticoloBean;
import bean.ImageBean;
import dao.ArticoloDAO;
import dao.ImageDAO;

public class ModelImageDAO implements ImageDAO{

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

	private static final String TABLE_NAME = "image";

	@Override
	public void doSave(ImageBean image) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;

		String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE nome = ? ";
		String insertSQL = "INSERT INTO " + TABLE_NAME +"(pathImage,articolo,nome) VALUES (?,?,?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(ifExists);
			ps.setString(1, image.getNome());
			if(!ps.executeQuery().next()) {
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, image.getPath());
				preparedStatement.setInt(2, image.getArticolo().getID());
				preparedStatement.setString(3, image.getNome());
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
	public boolean doDelete(String code, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND articolo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, id);

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
	public ImageBean doRetrieveByKey(String code, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArticoloDAO model = new MusicalModelArticoloBean();

		ImageBean img = null;
		ArticoloBean art = null;

		String selectSQL = "SELECT * FROM " + TABLE_NAME + "join articolo where nome = ? AND articolo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				img = new ImageBean();

				img.setNome(rs.getString("nome"));
		    	img.setPath(rs.getString("pathImage"));

		    	art = model.doRetrieveByKey(rs.getInt("articolo"));
		    	img.setArticolo(art);
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
		return img;
	}

	@Override
	public Collection<ImageBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArticoloDAO model = new MusicalModelArticoloBean();

		ImageBean img = null;
		ArticoloBean art = null;
		List<ImageBean> images = new LinkedList<ImageBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + "join articolo";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				img = new ImageBean();

				img.setNome(rs.getString("nome"));
		    	img.setPath(rs.getString("pathImage"));

		    	art = model.doRetrieveByKey(rs.getInt("articolo"));
		    	img.setArticolo(art);

		    	images.add(img);

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
		return images;
	}

	@Override
	public Collection<ImageBean> doRetrieveAllbyId(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArticoloDAO model = new MusicalModelArticoloBean();

		ImageBean img = null;
		ArticoloBean art = null;
		List<ImageBean> images = new LinkedList<ImageBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE articolo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				img = new ImageBean();

				img.setNome(rs.getString("nome"));
		    	img.setPath(rs.getString("pathImage"));

		    	art = model.doRetrieveByKey(rs.getInt("articolo"));
		    	img.setArticolo(art);

		    	images.add(img);

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
		return images;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doDeleteAll(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE articolo = ?";

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
	}