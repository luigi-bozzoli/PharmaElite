package managerOrdine;

import model.beans.ProdottoNellordineBean;

public interface ProdottoNellordineBeanDAO {
	public void doSave(ProdottoNellordineBean x);
	public ProdottoNellordineBean doRetrieveByKey(String x);
	public void doUpdate(ProdottoNellordineBean x);
	public void doDeleteByKey(String x);
}
