package dao;

import java.sql.SQLException;
import java.util.List;

import bean.ComposizioneBean;

public interface ComposizioneDAO extends MasterDAO<ComposizioneBean>{
	public List<ComposizioneBean> doRetrieveByAcq(int code) throws SQLException;
}
