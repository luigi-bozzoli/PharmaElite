function aggiornaPrezzo(x){

	var unita = $(x).val();

	//prezzo singolo prodotto
	var y = $(x).parent().prev().text();
	y =  y.replace("€", "");

	//prezzo totale
	var prezzo = unita * y;
	
	prezzo = approssima(prezzo);

	//aggiorna prezzo
	$(x).parent().next().html("€"+prezzo);
} 

function approssima(x){
	return Number.parseFloat(x).toFixed(2)
}
