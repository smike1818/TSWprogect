package dao;

import java.sql.SQLException;
import java.util.List;

import bean.ContoBean;
import bean.UserBean;

public interface ContoDAO extends MasterDAO <ContoBean>{
	
	public boolean doDelete(String code) throws SQLException;
	public ContoBean doRetrieveByKey(String code) throws SQLException;
	List<ContoBean> doRetrieveByUsr(UserBean user) throws SQLException;
	public boolean doPrefer(String iban, UserBean user) throws SQLException;
	public Object doRetrieveAll(String order, String cf) throws SQLException;
}
