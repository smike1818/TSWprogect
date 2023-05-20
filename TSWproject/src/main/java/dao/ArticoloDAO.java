package dao;

import java.sql.SQLException;
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
}
