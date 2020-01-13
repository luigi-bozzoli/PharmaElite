/**
 * 
 */
/**
 * 
 */
function validateForm(){
	var nomeProdotto=$("input[name=nomeProdotto]").val();
	var bNomeProdotto=verificaNome(nomeProdotto);

	
	var prezzoProdotto=$("input[name=prezzo]").val();
	var bPrezzoProdotto=verificaPrezzo(prezzoProdotto);
	

	var quantitaProdotto=$("input[name=quantita]").val();
	var bquantitaProdotto=verificaQuantita(quantitaProdotto);
	


	if(bNomeProdotto && bPrezzoProdotto && bquantitaProdotto){

		return true;
	}
	 
	window.scrollTo(0,0);
	return false;
}

function avviso(id,mex){
	$("#"+id).html(mex);
	$("#"+id).fadeIn("slow");
	$("#"+id).delay(1000).fadeOut("slow");
}

function verificaNome(nome){
	if(nome.length<1 || nome.length>30){
		avviso("nomeProdotto","La lunghezza deve essere compresa fra 1 e 30");
		return false;
	}
	return true;
}



function verificaPrezzo(price){
	price=price.replace(".","");
	
	var prezzoV=/^[0-9]+$/;
	if(String(price).match(prezzoV))
	{
		return true;
	}
	else{
		avviso("price","Il prezzo deve contenere solo numeri");
		return false;
	}
}
function verificaQuantita(quantita){
	var quantitaV=/^[0-9]+$/;
	if(String(quantita).match(quantitaV))
	{
		return true;
	}
	else
	{
		avviso("quantita","La quantita deve contenere solo numeri");
		return false;
	}
}