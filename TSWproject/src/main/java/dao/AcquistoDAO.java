package dao;

import java.sql.SQLException;

import bean.AcquistoBean;

public interface AcquistoDAO extends MasterDAO <AcquistoBean>{
	public AcquistoBean doRetrieveByKey(String cliente, String conto) throws SQLException;
}
