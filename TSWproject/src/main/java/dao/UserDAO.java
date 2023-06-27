package dao;

import java.sql.SQLException;
import java.util.Collection;

import bean.UserBean;

public interface UserDAO extends MasterDAO<UserBean> {
	
	public void doRetrieveByPermit(UserBean user) throws SQLException;
	public void doRetrieveByUsr(UserBean user) throws SQLException;
	public UserBean doRetrieveByKey(String code) throws SQLException;
	public boolean hasCard(String username) throws SQLException;
	public UserBean doRetrieveByUsr(String code) throws SQLException;
	public Collection<String> doRetrieveByIncompleteUsr(String value, String order) throws SQLException;
	public boolean checkEmail(String email) throws SQLException;
	
}
