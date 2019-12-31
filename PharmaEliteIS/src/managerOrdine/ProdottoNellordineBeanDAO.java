package managerOrdine;

import java.util.Set;

public interface ProdottoNellordineBeanDAO {
	public Set<String> retriveProductNames(String id);
	public void doSave(ProdottoNellordineBean x);
	public ProdottoNellordineBean doRetrieveByKey(String x);
	public void doUpdate(ProdottoNellordineBean x);
	public void doDeleteByKey(String x);
}
