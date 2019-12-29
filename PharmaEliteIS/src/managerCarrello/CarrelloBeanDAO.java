package managerCarrello;

import java.util.Set;

public interface CarrelloBeanDAO {
	public void deleteAllByEmail(String email);
	public void doSave(CarrelloBean c);
	public  Set<CarrelloBean> retriveAll(String email);
}
