package interfacceDAO;

import model.beans.ComposizioneBean;

public interface ComposizioneBeanDAO {
public void doSave(ComposizioneBean x);
public ComposizioneBean doRetrieveByKey(String x);
public void doUpdate(ComposizioneBean x);
public void doDeleteByKey(String x);
}
