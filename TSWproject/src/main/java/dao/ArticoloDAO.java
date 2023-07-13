package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import bean.ArticoloBean;

/*
 * ArticoloDAO è il dao di ArticoloBean che estende l'interfaccia
 * MasterDAO prendendo i metodi da esso per poi associare il tipo T di
 * MasterDAO ad ArticoloBean
 */
public interface ArticoloDAO extends MasterDAO<ArticoloBean>{
	
	//funzione per modificare la quantità degli elementi
	public void doChangeQuantity(int id, float q) throws SQLException;
	public String getFirstImage(int code) throws SQLException;
	public Collection<String> doRetrieveByIncompleteProduct(String value) throws SQLException;
	public List<String> getImages(int code) throws SQLException;
	public boolean modifyProduct(String newText, String field, int id) throws SQLException;
}
