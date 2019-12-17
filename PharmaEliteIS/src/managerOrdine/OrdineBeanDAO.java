package managerOrdine;

import model.beans.OrdineBean;

public interface OrdineBeanDAO {
	public void doSave(OrdineBean x);
	public OrdineBean doRetrieveByKey(String x);
	public void doUpdate(OrdineBean x);
	public void doDeleteByKey(String x);
}