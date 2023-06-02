package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AcquistoBean;
import bean.ContoBean;
import bean.UserBean;
import bean.IndirizzoBean;
import dao.AcquistoDAO;
import dao.ContoDAO;
import dao.IndirizzoDAO;
import dao.UserDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
		        
				String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE idAcquisto = ?";
				String insertSQL = "INSERT INTO " + TABLE_NAME +" (consumer, conto, importo, via, civico, citta) VALUES (?, ?, ?, ?, ?, ?)";

				try {
					connection = ds.getConnection();
					ps = connection.prepareStatement(ifExists);
					ps.setInt(1, acq.getID());
					if(!ps.executeQuery().next()) {
						preparedStatement = connection.prepareStatement(insertSQL);
						preparedStatement.setString(1, acq.getConsumer().getCF());
						preparedStatement.setString(2, acq.getConto().getIBAN());
						preparedStatement.setDouble(3, acq.getImporto());
						preparedStatement.setString(4, acq.getIndirizzo().getVia());
						preparedStatement.setString(6, acq.getIndirizzo().getCitta());
						preparedStatement.setInt(5, acq.getIndirizzo().getCivico());
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
		Connection connection = null;
		Collection<AcquistoBean>acq = new ArrayList<AcquistoBean>();    //collezione di username suggeriti
		PreparedStatement preparedStatement = null;
		AcquistoBean acquisto = null;
		ContoBean cont = null;
		UserBean in = null;
		IndirizzoBean ind = null;
		IndirizzoDAO modelind = new ModelIndirizzoDAO();
		UserDAO model = new ModelUserDAO();
		ContoDAO modelc = new ModelContoDAO();
        
	  try {
		//query per prelevare l'utente
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) { 
			acquisto = new AcquistoBean();
	    	
			acquisto.setID(rs.getInt("idAcquisto"));
			acquisto.setImporto(rs.getDouble("importo"));
			
			// Ottenere la data_acquisto come java.util.Date dal ResultSet
			Date dataAcquisto = rs.getTimestamp("data_acquisto");

			// Creare un oggetto SimpleDateFormat per il formato desiderato
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// Impostare il fuso orario a GMT
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

			// Formattare la data e l'ora nel formato desiderato
			String dataFormattata = sdf.format(dataAcquisto);

			// Assegnare la data formattata all'oggetto "acquisto"
			acquisto.setDate(dataFormattata);
			
	    	in = model.doRetrieveByKey(rs.getString("consumer"));
	    	cont = modelc.doRetrieveByKey(rs.getString("conto"));
	    	
	    	acquisto.setConsumer(in);
	    	acquisto.setConto(cont);
	    	
	    	ind = modelind.doRetrieveByKey(rs.getString("via"), rs.getInt("civico"), rs.getString("citta"));
	    	acquisto.setIndirizzo(ind);
	    	
	    	acq.add(acquisto);
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
	  return acq;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AcquistoBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AcquistoBean acq = null;
		ContoBean cont = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		ContoDAO modelc = new ModelContoDAO();
		IndirizzoBean ind = null;
		IndirizzoDAO modelind = new ModelIndirizzoDAO();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " idAcquisto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				acq = new AcquistoBean();
		    	
		    	acq.setImporto(rs.getDouble("importo"));
		    	in = model.doRetrieveByKey(rs.getString("consumer"));
		    	cont = modelc.doRetrieveByKey(rs.getString("conto"));
		    	
		    	// Ottenere la data_acquisto come java.util.Date dal ResultSet
		    	Date dataAcquisto = rs.getTimestamp("data_acquisto");

		    	// Creare un oggetto SimpleDateFormat per il formato desiderato
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    	// Impostare il fuso orario a GMT
		    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		    	// Formattare la data e l'ora nel formato desiderato
		    	String dataFormattata = sdf.format(dataAcquisto);

		    	// Assegnare la data formattata all'oggetto "acquisto"
		    	acq.setDate(dataFormattata);
		    	
		    	acq.setConsumer(in);
		    	acq.setConto(cont);
		    	
		    	ind = modelind.doRetrieveByKey(rs.getString("via"), rs.getInt("civico"), rs.getString("citta"));
		    	acq.setIndirizzo(ind);
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
	public AcquistoBean doRetrieveBeanDesc() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AcquistoBean acq = null;
		ContoBean cont = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		ContoDAO modelc = new ModelContoDAO();
		IndirizzoBean ind = null;
		IndirizzoDAO modelind = new ModelIndirizzoDAO();
		
		String selectSQL = "SELECT * FROM " +TABLE_NAME+ " ORDER BY idAcquisto DESC LIMIT 1";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				acq = new AcquistoBean();
		    	
				acq.setID(rs.getInt("idAcquisto"));
		    	acq.setImporto(rs.getDouble("importo"));
		    	in = model.doRetrieveByKey(rs.getString("consumer"));
		    	cont = modelc.doRetrieveByKey(rs.getString("conto"));
		    	
		    	// Ottenere la data_acquisto come java.util.Date dal ResultSet
		    	Date dataAcquisto = rs.getTimestamp("data_acquisto");

		    	// Creare un oggetto SimpleDateFormat per il formato desiderato
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    	// Impostare il fuso orario a GMT
		    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		    	// Formattare la data e l'ora nel formato desiderato
		    	String dataFormattata = sdf.format(dataAcquisto);

		    	// Assegnare la data formattata all'oggetto "acquisto"
		    	acq.setDate(dataFormattata);
		    	
		    	acq.setConsumer(in);
		    	acq.setConto(cont);
		    	
		    	ind = modelind.doRetrieveByKey(rs.getString("via"), rs.getInt("civico"), rs.getString("citta"));
		    	acq.setIndirizzo(ind);
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
	public Collection<AcquistoBean> doRetrieveByUser(String cf) throws SQLException{
		Connection connection = null;
		Collection<AcquistoBean>acq = new ArrayList<AcquistoBean>();    //collezione di username suggeriti
		PreparedStatement preparedStatement = null;
		AcquistoBean acquisto = null;
		ContoBean cont = null;
		UserBean in = null;
		UserDAO model = new ModelUserDAO();
		ContoDAO modelc = new ModelContoDAO();
		IndirizzoBean ind = null;
		IndirizzoDAO modelind = new ModelIndirizzoDAO();
        
	  try {
		//query per prelevare l'utente
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE consumer = ?";
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setString(1, cf);
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) { 
			acquisto = new AcquistoBean();
	    	
			acquisto.setID(rs.getInt("idAcquisto"));
			acquisto.setImporto(rs.getDouble("importo"));
	    	in = model.doRetrieveByKey(rs.getString("consumer"));
	    	cont = modelc.doRetrieveByKey(rs.getString("conto"));
	    	
	    	// Ottenere la data_acquisto come java.util.Date dal ResultSet
	    	Date dataAcquisto = rs.getTimestamp("data_acquisto");

	    	// Creare un oggetto SimpleDateFormat per il formato desiderato
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    	// Impostare il fuso orario a GMT
	    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

	    	// Formattare la data e l'ora nel formato desiderato
	    	String dataFormattata = sdf.format(dataAcquisto);

	    	// Assegnare la data formattata all'oggetto "acquisto"
	    	acquisto.setDate(dataFormattata);
	    	
	    	acquisto.setConsumer(in);
	    	acquisto.setConto(cont);
	    	
	    	ind = modelind.doRetrieveByKey(rs.getString("via"), rs.getInt("civico"), rs.getString("citta"));
	    	acquisto.setIndirizzo(ind);
	    	
	    	acq.add(acquisto);
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
	  return acq;
	}

}
