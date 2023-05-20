package dao;

import java.sql.SQLException;

import bean.UserBean;

public interface UserDAO extends MasterDAO<UserBean> {
	
	public void doRetrieveByPermit(UserBean user) throws SQLException;
	public void doRetrieveByUsr(UserBean user) throws SQLException;
	public UserBean doRetrieveByKey(String code) throws SQLException;
	public boolean hasCard(String username) throws SQLException;
	public UserBean doRetrieveByUsr(String code) throws SQLException;
	
}
