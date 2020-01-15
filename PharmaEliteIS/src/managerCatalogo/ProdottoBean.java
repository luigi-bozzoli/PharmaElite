package managerCatalogo;

public class ProdottoBean implements Comparable<ProdottoBean> {
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
		boolean descrizioneCheck = controlloDescrizione(this.Descrizione);

		return  urlCheck && nomeCheck && prezzoCheck && categoriaCheck && quantitaCheck && descrizioneCheck;
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

	private boolean controlloDescrizione(String s) {
		boolean temp;

		for(int i=0;i<s.length();i++){
			char ch = s.charAt(i);
			if (Character.isLetter(ch) || ch == ' ') {
				continue;
			}
			temp = false;
		}
		temp = true;

		
		
		if(s.replace(" ", "").length() == 0) {
			temp = false;
		}
		
		if(s.replace("\n", "").length() == 0) {
			temp = false;
		}

		System.out.println(s.length());


		if(s.length() <= 0 || s.length() > 256)
			return temp && false;
		else
			return temp && true;
	}

	private boolean checkQuantita(int q) {


		if(q<0)
			return false;
		else return true;
	}

	@Override
	public int compareTo(ProdottoBean o) {

		return this.Nome.compareToIgnoreCase(o.getNome());
	}
	
	public boolean equals(Object o) {
		  if (o == null) return false;
		    if (o == this) return true;
		    if (!(o instanceof ProdottoBean))return false;
		    ProdottoBean p = (ProdottoBean) o;

		    boolean id = this.getId().equalsIgnoreCase(p.getId());
		    boolean cat = this.getCategoria().equalsIgnoreCase(p.getCategoria());
		    boolean descrizione = this.getDescrizione().equalsIgnoreCase(p.getDescrizione());
		    boolean nome = this.getNome().equalsIgnoreCase(p.getNome());
		    boolean prezzo = this.getPrezzo() == p.getPrezzo();
		    boolean quantita = this.getQuantita() == p.getQuantita();
		    boolean url = this.getUrlImmagine().equalsIgnoreCase(p.getUrlImmagine());
		    
		  
		    
		    return id && cat && descrizione && nome && prezzo && quantita && url;
	}
}
