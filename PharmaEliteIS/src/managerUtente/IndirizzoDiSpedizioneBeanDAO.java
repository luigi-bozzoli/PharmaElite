package managerUtente;


public interface IndirizzoDiSpedizioneBeanDAO {
	public void doSave(IndirizzoDiSpedizioneBean x);
	public IndirizzoDiSpedizioneBean doRetrieveByKey(String x);
	public IndirizzoDiSpedizioneBean doRetrieveByEmail(String email);
	public void doUpdate(IndirizzoDiSpedizioneBean x);
	public void doDeleteByKey(String x);
}
