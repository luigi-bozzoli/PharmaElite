
function getCostoTot(){
	var tot =  $("#totale").text();
	tot = tot.replace("Totale", "");
	tot = parseFloat(tot);
	return tot;
}

function aggiornaCostoTot(tot){
	tot = approssima(tot);
	$("strong").html("Totale "+tot);
}


function approssima(x){
	return Number.parseFloat(x).toFixed(2);
}

function alertMod(button, boolean){

	if(boolean){
		$(button).parent().siblings().find("h3").html("Le modifiche non solo salvate");
		$(button).parent().siblings().find("h3").fadeIn("slow");
	}else {
		$(button).parent().siblings().find("h3").fadeOut("slow");
	}

}

function aggiornaPrezzo(x){

	var unita = $(x).val();

	//prezzo singolo prodotto
	var y = $(x).parent().prev().text();
	y =  y.replace("€", "");

	//prezzo pre-calcolo
	var prePrezzo = $(x).parent().next().text();
	prePrezzo = prePrezzo.replace("€", "");

	//prezzo totale
	var prezzo = unita * y;

	//aggiorna prezzo
	$(x).parent().next().html("€"+prezzo);

	//costo totale
	var tot = getCostoTot();

	if(prePrezzo < prezzo){ //aggiunta di prodotti
		tot += (prezzo - prePrezzo);
	}else { //rimozione prodotti
		tot -= (prePrezzo-prezzo);
	}

	aggiornaCostoTot(tot);

	var defaultVal = $(x).prev().val();

	if(defaultVal == unita){
		alertMod(x, false);
	}else{
		alertMod(x, true);
	}
}

//AJAX
function avviso(button){
	$(button).parent().siblings().find("h3").fadeIn("slow");
	$(button).parent().siblings().find("h3").delay(1000).fadeOut("slow");
}

function emptyCart(){
	$("#emptyBody").toggle();
	$("#emptyFoot").toggle();

	$("#fullBody").toggle();
	$("#fullHead").toggle();
	$("#fullFoot").toggle();
}

function eliminaProdotto(button){
	//prezzo prodotti rimossi
	var x =	$(button).parent().prev().text();
	x = x.replace("€", "");

	var tot = getCostoTot();
	tot -= x;
	aggiornaCostoTot(tot);

//	rimuovo tr
	$(button).parent().parent().remove();

//	carrello vuoto
	if(tot == 0){
		emptyCart();
	}

}

function rimuoviDalCarrello(){
	var idProd =	$(this).prev().val();
	//richiesta AJAX
	requestDelete(idProd, this);
}

function requestDelete(idProd, button){

	var id = "id="+idProd;
	var qString = "?"+id;
	var url = "RimuoviDalCarrello"+qString;


	$.get(url, function(data, status){
		
		if(data == "true"){
			eliminaProdotto(button);
		}else{
			$(button).parent().siblings().find("h3").html("Si è verificato un errore");
			avviso(button);
		}
	});
}


//AJAX MODIFICA carrello
function modificaCarrello(){
	var button = this;
	var quantità =	$(this).parent().siblings().find("[type = number]").val();
	var idProd =	$(this).next().val();

	if(quantità == 0){
		requestDelete(idProd, this);
	}

	var id = "id="+idProd;
	var qString = "?"+id+"&"+"quantità="+quantità;
	var url = "ModificaCarrello"+qString;
	
	$.get(url, function(data, status){
		if(data == "true"){
			$(button).parent().siblings().find("[name = defaultQuantity]").val(quantità);
			alertMod(button, false);			
		}else{
			$(button).parent().siblings().find("h3").html("Sono disponibili solo " + data + " unità");
			avviso(button);
		}
	});
}

$(document).ready(function(){
	$(".btn-danger").click(rimuoviDalCarrello);
	$(".btn-info").click(modificaCarrello);

})
