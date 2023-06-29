package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.IndirizzoBean;
import bean.UserBean;
import dao.IndirizzoDAO;
import dao.UserDAO;

public class ModelIndirizzoDAO implements IndirizzoDAO{

	private static DataSource ds;
	private UserDAO usermodel = new ModelUserDAO();

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/musicalstore");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "indirizzo";
	
	@Override
	public void doSave(IndirizzoBean ind) throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				PreparedStatement ps = null;
		        
				String ifExists = "SELECT * FROM " + TABLE_NAME +" WHERE via = ? AND civico = ? AND citta = ?";
				String insertSQL = "INSERT INTO " + TABLE_NAME +" (via,civico,citta,cap,cliente) VALUES (?, ?, ?, ?, ?)";

				try {
					connection = ds.getConnection();
					ps = connection.prepareStatement(ifExists);
					ps.setString(1, ind.getVia());
					ps.setInt(2, ind.getCivico());
					ps.setString(3, ind.getCitta());
					if(!ps.executeQuery().next()) {
						preparedStatement = connection.prepareStatement(insertSQL);
						preparedStatement.setString(1, ind.getVia());
						preparedStatement.setInt(2, ind.getCivico());
						preparedStatement.setString(3, ind.getCitta());
						preparedStatement.setInt(4, ind.getCap());
						preparedStatement.setString(5, ind.getCliente().getCF());
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
	public IndirizzoBean doRetrieveByKey(String via, int civico, String citta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		IndirizzoBean address = null;
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE via = ? AND civico = ? AND citta = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1,via);
            preparedStatement.setInt(2,civico);
            preparedStatement.setString(3,citta);
    		String CFuser = null;
    		UserBean user = null;
    		UserDAO model = new ModelUserDAO();
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				address = new IndirizzoBean();
								
				address.setCap(rs.getInt("cap"));
				address.setCitta(rs.getString("citta"));
				address.setCivico(rs.getInt("civico"));
				address.setVia(rs.getString("via"));
				address.setIsPrimary(rs.getBoolean("isPrimary"));
				
				CFuser = rs.getString("cliente");
		    	
				try {
		    	   user = model.doRetrieveByKey(CFuser);
				}catch(SQLException e) {
					user=new UserBean();
					user.setCF(CFuser);
				}
		    	
		    	address.setCliente(user);	
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
		return address;
	}

	@Override
	public Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException {
		return null;		
	}

	@Override
	public boolean doDelete(String via, int civico, String citta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE via = ? AND civico = ? AND citta = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, via);
			preparedStatement.setInt(2, civico);
			preparedStatement.setString(3, citta);
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
	public IndirizzoBean doRetrieveByKey(String CF) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		IndirizzoBean ind = new IndirizzoBean();
		UserBean user = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE cliente = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, CF);
		
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) { 
			    ind.setCap(rs.getInt("cap"));
			    ind.setCitta(rs.getString("citta"));
			    ind.setCivico(rs.getInt("civico"));
			    ind.setVia(rs.getString("via"));
			    ind.setIsPrimary(rs.getBoolean("isPrimary"));
			    
			    try {
			    	   user = usermodel.doRetrieveByKey(CF);
					}catch(SQLException e) {
						user=new UserBean();
						user.setCF(CF);
					}
			    
			    ind.setCliente(user);
		}else 
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
	  
	  return ind;
	}

	@Override
	public IndirizzoBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<IndirizzoBean> doRetrieveByUsr(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		IndirizzoBean address = null;
		List<IndirizzoBean>list_address = new ArrayList<IndirizzoBean>();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE cliente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, user.getCF());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				address = new IndirizzoBean();
								
				address.setCap(rs.getInt("cap"));
				address.setCitta(rs.getString("citta"));
				address.setCivico(rs.getInt("civico"));
				address.setVia(rs.getString("via"));	
				address.setIsPrimary(rs.getBoolean("isPrimary"));
		    	address.setCliente(user);	
		    	
		    	list_address.add(address);
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
		return list_address;
	}

	@Override
	public boolean doDelete(String CF) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean doPrefer(String via, int civico, String citta, UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;

		int result = 0;
		String updateToFalse = "UPDATE "+ TABLE_NAME + " SET isPrimary = ? WHERE isPrimary = ? AND cliente = ?";
		String updateSQL = "UPDATE " + TABLE_NAME + " SET isPrimary = ? WHERE via = ? AND civico = ? AND citta = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(updateToFalse);
			ps.setBoolean(1, false);
			ps.setBoolean(2, true);
			ps.setString(3, user.getCF());
			
			if(ps.executeUpdate()>=0) {
			
			  preparedStatement = connection.prepareStatement(updateSQL);
			  preparedStatement.setBoolean(1, true);
			  preparedStatement.setString(2,via);
			  preparedStatement.setInt(3,civico);
			  preparedStatement.setString(4,citta);

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
	public Collection<IndirizzoBean> doRetrieveAll(String string, String cf) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		IndirizzoBean address = null;
		String CFuser = null;
		UserBean user = null;
		UserDAO model = new ModelUserDAO();

		List<IndirizzoBean> indlist = new LinkedList<IndirizzoBean>();
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE cliente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				address = new IndirizzoBean();
								
				address.setCap(rs.getInt("cap"));
				address.setCitta(rs.getString("citta"));
				address.setCivico(rs.getInt("civico"));
				address.setVia(rs.getString("via"));
				address.setIsPrimary(rs.getBoolean("isPrimary"));
				
				CFuser = rs.getString("cliente");
		    	
		    	user = model.doRetrieveByKey(CFuser);
		    	
		    	address.setCliente(user);	
		    	indlist.add(address);
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
		return indlist;
	}

	@Override
	public boolean checkAddress(String via, String civico, String cap) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        
	  try {
		String insertSQL = "SELECT * FROM " + TABLE_NAME +" WHERE via = ? AND civico = ? AND cap = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, via);
		preparedStatement.setString(2, civico);
		preparedStatement.setString(3, cap);
		
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