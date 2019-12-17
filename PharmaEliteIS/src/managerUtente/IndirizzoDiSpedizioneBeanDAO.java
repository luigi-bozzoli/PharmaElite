package managerUtente;

import model.beans.IndirizzoDiSpedizioneBean;

public interface IndirizzoDiSpedizioneBeanDAO {
	public void doSave(IndirizzoDiSpedizioneBean x);
	public IndirizzoDiSpedizioneBean doRetrieveByKey(String x);
	public void doUpdate(IndirizzoDiSpedizioneBean x);
	public void doDeleteByKey(String x);
}
