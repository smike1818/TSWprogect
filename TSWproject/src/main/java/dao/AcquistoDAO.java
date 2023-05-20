package dao;

import java.sql.SQLException;

import bean.AcquistoBean;

public interface AcquistoDAO extends MasterDAO <AcquistoBean>{
     public AcquistoBean doRetrieveBeanDesc() throws SQLException; 
}
