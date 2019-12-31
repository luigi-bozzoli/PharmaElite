package managerOrdine;

import java.util.Set;

public interface OrdineBeanDAO {
	public Set<OrdineBean> retriveAll(String email);
	public void doSave(OrdineBean x);
	public OrdineBean doRetrieveByKey(String x);
	public void doUpdate(OrdineBean x);
	public void doDeleteByKey(String x);
}
