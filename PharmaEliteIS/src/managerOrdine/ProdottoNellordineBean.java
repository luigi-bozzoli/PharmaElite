package managerOrdine;

public class ProdottoNellordineBean {
private String Id,Nome,IdProdotto;
private double prezzo;
public ProdottoNellordineBean() {
	
}
public String getNome() {
	return Nome;
}
public void setNome(String nome) {
	Nome = nome;
}
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getIdProdotto() {
	return IdProdotto;
}
public void setIdProdotto(String idProdotto) {
	IdProdotto = idProdotto;
}
public double getPrezzo() {
	return prezzo;
}
public void setPrezzo(double prezzo) {
	this.prezzo = prezzo;
}

}
