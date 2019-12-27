package managerCatalogo;

import java.util.Set;

public interface ProdottoBeanDAO {
	public void doSave(ProdottoBean x);
	public ProdottoBean doRetrieveByKey(String x);
	public void doUpdate(ProdottoBean x);
	public void doDeleteByKey(String x);
	public Set<ProdottoBean> searchByName(String nome);
	public Set<ProdottoBean> searchByCategory(String categoria);
	public Set<ProdottoBean> retriveAll();
}
