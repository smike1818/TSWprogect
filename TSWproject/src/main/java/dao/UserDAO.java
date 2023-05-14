package dao;

import java.sql.SQLException;

import bean.UserBean;

public interface UserDAO extends MasterDAO<UserBean> {
	
	public void doRetrieveByPermit(UserBean user) throws SQLException;
	public void doRetrieveByUsr(UserBean user) throws SQLException;
	
}
