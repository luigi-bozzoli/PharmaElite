package managerUtente;

import model.beans.DatiAnagraficiBean;

public interface DatiAnagraficiBeanDAO {
public void doSave(DatiAnagraficiBean x);
public DatiAnagraficiBean doRetrieveByKey(String x);
public void doUpdate(DatiAnagraficiBean x);
public void doDeleteByKey(String x);
}
