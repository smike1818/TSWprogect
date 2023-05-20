package dao;

import java.sql.SQLException;

import bean.ContoBean;

public interface ContoDAO extends MasterDAO <ContoBean>{
	
	public boolean doDelete(String code) throws SQLException;
	public ContoBean doRetrieveByKey(String code) throws SQLException;
}
