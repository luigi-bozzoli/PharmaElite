package managerUtente;

import java.util.Set;

public interface IndirizzoDiSpedizioneBeanDAO {
	public Set<IndirizzoDiSpedizioneBean> doRetrieveAll(String email);
	public void doSave(IndirizzoDiSpedizioneBean x);
	public IndirizzoDiSpedizioneBean doRetrieveByKey(String x);
	public IndirizzoDiSpedizioneBean doRetrieveByEmail(String email);
	public void doUpdate(IndirizzoDiSpedizioneBean x);
	public void doDeleteByKey(String x);
}
