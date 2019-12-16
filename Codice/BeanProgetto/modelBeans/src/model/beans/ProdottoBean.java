package model.beans;

public class ProdottoBean {
private String Id,UrlImmagine,Categoria,Nome,Descrizione;
private int Quantita;
private double Prezzo;
private boolean FlagEliminato;
public ProdottoBean() {
	
}
public String getDescrizione() {
	return Descrizione;
}
public void setDescrizione(String descrizione) {
	Descrizione = descrizione;
}
public String getCategoria() {
	return Categoria;
}
public void setCategoria(String categoria) {
	Categoria = categoria;
}
public String getNome() {
	return Nome;
}
public void setNome(String nome) {
	Nome = nome;
}
public double getPrezzo() {
	return Prezzo;
}
public void setPrezzo(double prezzo) {
	Prezzo = prezzo;
}
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getUrlImmagine() {
	return UrlImmagine;
}
public void setUrlImmagine(String urlImmagine) {
	UrlImmagine = urlImmagine;
}
public int getQuantita() {
	return Quantita;
}
public void setQuantita(int quantita) {
	Quantita = quantita;
}
public boolean isFlagEliminato() {
	return FlagEliminato;
}
public void setFlagEliminato(boolean flagEliminato) {
	FlagEliminato = flagEliminato;
}
}
