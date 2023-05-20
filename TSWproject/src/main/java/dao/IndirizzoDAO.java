package dao;

import java.sql.SQLException;

import bean.IndirizzoBean;

public interface IndirizzoDAO extends MasterDAO <IndirizzoBean> {
	public boolean doDelete(String CF) throws SQLException;
	public IndirizzoBean doRetrieveByKey(String CF) throws SQLException;
}
