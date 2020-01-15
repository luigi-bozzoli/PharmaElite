package managerUtente;

import java.util.Set;

public interface MetodoDiPagamentoBeanDAO {
	public Set<MetodoDiPagamentoBean> doRetrieveAll(String email);
	public void doSave(MetodoDiPagamentoBean x);
	public MetodoDiPagamentoBean doRetrieveByKey(String numero, String email);
	public void doUpdate(MetodoDiPagamentoBean x);
	public void doDeleteByKey(String numero, String email);
}
