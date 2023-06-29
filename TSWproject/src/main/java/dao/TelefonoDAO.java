package dao;

import java.util.List;

import java.sql.SQLException;
import bean.TelefonoBean;
import bean.UserBean;

public interface TelefonoDAO extends MasterDAO<TelefonoBean>{
   public List<TelefonoBean> doRetrieveAllPhones(UserBean user) throws SQLException;
   boolean doDelete(String code) throws SQLException;
   boolean doPrefer(String numero, UserBean user) throws SQLException;
   public boolean checkPhone(String data) throws SQLException;
}
