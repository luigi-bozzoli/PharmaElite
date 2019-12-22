package managerUtente;


public interface ClienteBeanDAO {
public void doSave(ClienteBean x);
public ClienteBean doRetrieveByKey(String x);
public void doUpdate(ClienteBean x);
public void doDeleteByKey(String x);
public ClienteBean login(String x, String y);
}
