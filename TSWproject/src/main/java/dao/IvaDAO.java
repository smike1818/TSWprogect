package dao;

import java.sql.SQLException;

import bean.IvaBean;

public interface IvaDAO  {

	IvaBean getIvaByModel() throws SQLException;
	boolean changeIva(IvaBean iva) throws SQLException;
}
