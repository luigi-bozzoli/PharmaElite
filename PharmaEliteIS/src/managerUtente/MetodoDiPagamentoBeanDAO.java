package managerUtente;

public interface MetodoDiPagamentoBeanDAO {
	public void doSave(MetodoDiPagamentoBean x);
	public MetodoDiPagamentoBean doRetrieveByKey(String numero, String email);
	public void doUpdate(MetodoDiPagamentoBean x);
	public void doDeleteByKey(String x);
}
