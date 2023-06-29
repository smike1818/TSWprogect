package dao;

import java.sql.SQLException;
import java.util.Collection;
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
	public Collection<IndirizzoBean> doRetrieveAll(String string, String cf) throws SQLException;
	public boolean checkAddress(String via, String civico, String cap) throws SQLException;
}
