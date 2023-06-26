package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import bean.AcquistoBean;
import bean.ArticoloBean;

public interface AcquistoDAO extends MasterDAO <AcquistoBean>{
     public AcquistoBean doRetrieveBeanDesc() throws SQLException;

	public Collection<AcquistoBean> doRetrieveByUser(String cf) throws SQLException;
    public List<ArticoloBean> getArticoliByAcquisti(int IDAcquisto) throws SQLException;
}
