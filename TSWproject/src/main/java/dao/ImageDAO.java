package dao;

import java.sql.SQLException;
import java.util.Collection;

import bean.ImageBean;

public interface ImageDAO extends MasterDAO <ImageBean> {
     
	public Collection<ImageBean> doRetrieveAllbyId(int code) throws SQLException;
	boolean doDelete(String code, int id) throws SQLException;
	ImageBean doRetrieveByKey(String code, int id) throws SQLException;
	public boolean doDeleteAll(int code) throws SQLException;
}
