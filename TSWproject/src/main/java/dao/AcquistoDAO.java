package dao;

import java.sql.SQLException;
import java.util.Collection;

import bean.AcquistoBean;

public interface AcquistoDAO extends MasterDAO <AcquistoBean>{
     public AcquistoBean doRetrieveBeanDesc() throws SQLException;

	public Collection<AcquistoBean> doRetrieveByUser(String cf) throws SQLException; 
}
