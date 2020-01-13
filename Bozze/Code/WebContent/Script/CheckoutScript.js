/**
 * 
 */

function mostraDiv(x){
	$(x).fadeIn();
}

function modificaPrezzoSped(id, prezzo){
	$("#"+id).text("EUR "+prezzo);
}

function prezzoSped(metodoDiSpedizione, totOrdine){
	if(totOrdine >= 49.99){
		modificaPrezzoSped("prezzoSped", 0);
		return 0;
	}


	if(metodoDiSpedizione == "gratuita"){
		modificaPrezzoSped("prezzoSped", 0);
		return 0;
	}
	if(metodoDiSpedizione == "standard"){
		modificaPrezzoSped("prezzoSped", 4);
		return 4;
	}
	if(metodoDiSpedizione == "rapida"){
		modificaPrezzoSped("prezzoSped", 5);

		return 5;
	}
}

function aggiornaPrezzo(){
	var metodoDiSpedizione=$("select[name=spedizione]").val();


	var totOrdine = $("#prezzoProdotti").text();
	totOrdine = totOrdine.replace("EUR", "");
	totOrdine = parseFloat(totOrdine);
	
	var prezzo = prezzoSped(metodoDiSpedizione, totOrdine);

	totOrdine += prezzo;

	$("#totOrdine").text("EUR "+totOrdine);

}
function checkIndirizzo(){
	var indirizzo=$("input[name=indirizzoSped]").val();
	if(indirizzo.length == 0 || indirizzo.length()>50){
		$("#ind").fadeIn("slow");
		$("#ind").fadeOut("slow");
		return false;
	}
	return true;
}
function checkCarta(){
	var carta=$("input[name=numCarta]").val();
	if(carta.length!=16){
	avvisaCarta();
		return false;
	}

	var num=/^[0-9]+$/;
	if(String(carta).match(num)){
	
		return true;
}
	else{
		avvisaCarta();
		return false;
	}
	}
function avvisaCarta(){
	$("#numeroCarta").fadeIn("slow");
	$("#numeroCarta").delay(1000).fadeOut("slow");
}
