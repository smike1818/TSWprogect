package dao;

import java.sql.SQLException;
import java.util.Collection;

/*
 * MasterDAO è il DAO principale del progetto,  
 * in cui ci sono tutti i metodi base per connettere qualsiasi bean al Database
 * questa classe è ovviamente parametrizzata
*/

public interface MasterDAO<T> {
	public void doSave(T product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public T doRetrieveByKey(int code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;

}
