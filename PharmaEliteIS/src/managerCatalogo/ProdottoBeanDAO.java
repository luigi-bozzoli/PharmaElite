package managerCatalogo;

import java.util.List;

public interface ProdottoBeanDAO {
	public void doSave(ProdottoBean x);
	public ProdottoBean doRetrieveByKey(String x);
	public void doUpdate(ProdottoBean x);
	public void doDeleteByKey(String x);
	public List<ProdottoBean> searchByName(String nome);
	public List<ProdottoBean> searchByCategory(String categoria);
}
