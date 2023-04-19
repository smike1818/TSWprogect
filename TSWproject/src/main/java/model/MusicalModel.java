package model;

import java.sql.SQLException;
import java.util.Collection;
import bean.ArticoloBean;

public interface MusicalModel {
	public void doSave(ArticoloBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ArticoloBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ArticoloBean> doRetrieveAll(String order) throws SQLException;
}
