package managerOrdine;


public interface ComposizioneBeanDAO {
public void doSave(ComposizioneBean x);
public ComposizioneBean doRetrieveByKey(String x, String y);
public void doUpdate(ComposizioneBean x);
public void doDeleteByKey(String x, String y);
}
