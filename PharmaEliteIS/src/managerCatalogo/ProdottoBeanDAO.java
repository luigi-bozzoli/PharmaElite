package managerCatalogo;


public interface ProdottoBeanDAO {
	public void doSave(ProdottoBean x);
	public ProdottoBean doRetrieveByKey(String x);
	public void doUpdate(ProdottoBean x);
	public void doDeleteByKey(String x);
}
