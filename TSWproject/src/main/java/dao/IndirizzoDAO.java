package dao;

import java.sql.SQLException;
import java.util.List;

import bean.IndirizzoBean;
import bean.UserBean;

public interface IndirizzoDAO extends MasterDAO <IndirizzoBean> {
	public boolean doDelete(String CF) throws SQLException;
	public IndirizzoBean doRetrieveByKey(String CF) throws SQLException;
	IndirizzoBean doRetrieveByKey(String via, int civico, String citta) throws SQLException;
	List<IndirizzoBean> doRetrieveByUsr(UserBean user) throws SQLException;
	boolean doDelete(String via, int civico, String citta) throws SQLException;
	boolean doPrefer(String via, int civico, String citta, UserBean user) throws SQLException;
}
