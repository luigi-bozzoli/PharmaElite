package managerCarrello;

public class QuantitaNonDisponibile extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int quantitaDisponibile;

	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public QuantitaNonDisponibile(int quantita) {
		super();
		this.quantitaDisponibile = quantita;
	}
}
