package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.*;
import dao.*;

public class ModelComposizioneDAO implements ComposizioneDAO{
	

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
	
	private static final String TABLE_NAME = "composizione";

	@Override
	public void doSave(ComposizioneBean comp) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;
        
		String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE articolo = ? AND acquisto = ?";
		String insertSQL = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(ifExists);
			ps.setInt(1, comp.getArticolo().getID());
			ps.setInt(2, comp.getAcquisto().getID());
			if(!ps.executeQuery().next()) {
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setDouble(1, comp.getPrezzo());
				preparedStatement.setInt(3, comp.getIva());
				preparedStatement.setInt(4, comp.getArticolo().getID());
				preparedStatement.setInt(5, comp.getAcquisto().getID());
				preparedStatement.setInt(2, comp.getqAcquistate());
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
	public ComposizioneBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ComposizioneBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComposizioneBean> doRetrieveByAcq(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
		AcquistoDAO modelacq = new ModelAcquistoDAO();
		ArticoloDAO modelart = new MusicalModelArticoloBean();
		
		List<ComposizioneBean> complist = new ArrayList<ComposizioneBean>();
		AcquistoBean acq = null;
		ArticoloBean art = null;
		ComposizioneBean comp = null;
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE acquisto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
		    	comp = new ComposizioneBean();
		    	
		    	comp.setIva(rs.getInt("iva"));
		    	comp.setqAcquistate(rs.getInt("qAcquistate"));
		    	comp.setPrezzo(rs.getDouble("prezzo"));
		    	
		    	acq = modelacq.doRetrieveByKey(rs.getInt("acquisto"));
		    	art = modelart.doRetrieveByKey(rs.getInt("articolo"));
		    	comp.setAcquisto(acq);
		    	comp.setArticolo(art);
		    	
		    	complist.add(comp);		    	
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
		return complist;
	}

}
