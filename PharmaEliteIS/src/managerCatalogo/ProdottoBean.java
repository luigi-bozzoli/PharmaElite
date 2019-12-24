package managerCatalogo;

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

	public boolean validate() {
		boolean urlCheck = controlloUrl(this.UrlImmagine);
		boolean nomeCheck = controlloNome(this.Nome);
		boolean prezzoCheck = controlloPrezzo(this.Prezzo);
		boolean categoriaCheck = controlloCategoria(this.Categoria);
		boolean quantitaCheck = checkQuantita(this.Quantita);

		return  urlCheck && nomeCheck && prezzoCheck && categoriaCheck && quantitaCheck;
	}



	private boolean controlloUrl(String url) {
		if(url.length() > 256)
			return false;

		return true;
	}

	private boolean controlloNome(String nome) {
		if(nome.length() > 30)
			return false;

		return true;
	}

	private boolean controlloPrezzo(double prezzo) {

		if(prezzo<0) {
			return false;
		}else {
			return true;
		}
	}
	
	 private boolean controlloCategoria(String cat) {
		 boolean b1 =cat.equalsIgnoreCase("Igiene orale");
		 boolean b2 = cat.equalsIgnoreCase("farmaci da banco");
		 boolean b3 = cat.equalsIgnoreCase("erboristeria");
		 boolean b4 =cat.equalsIgnoreCase("integratori");
		 
		 return(b1||b2||b3||b4);
	 }
	 
	 private boolean checkQuantita(int q) {

			
			if(q<0)
				return false;
			else return true;
		}
}
