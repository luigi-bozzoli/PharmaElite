package managerUtente;

import model.beans.MetodoDiPagamentoBean;

public interface MetodoDiPagamentoBeanDAO {
	public void doSave(MetodoDiPagamentoBean x);
	public MetodoDiPagamentoBean doRetrieveByKey(String x);
	public void doUpdate(MetodoDiPagamentoBean x);
	public void doDeleteByKey(String x);
}
