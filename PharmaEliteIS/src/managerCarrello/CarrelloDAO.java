package managerCarrello;

public interface CarrelloDAO {
	public void deleteAllByEmail(String email);
	public void doSave(CarrelloBean c);
}
