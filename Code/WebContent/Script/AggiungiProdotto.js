function validaForm(){
	var nome=$("input[name=nome]").val();
	var nomeB=verificaNome(nome);
	var prezzo=$("input[name=prezzo]").val();
	var prezzoB=controllaPrezzo(prezzo);
	var quantita=$("input[name=quantita]").val();
	var quantitaB=controlloQuantita(quantita);
	var id=$("input[name=id]").val();
	var idB=validaId(id);
	return(nomeB&&prezzoB&&quantitaB&&idB);
}
function verificaNome(nome){
	if(nome.length==0){
		avviso("nome","Il campo è obbligatorio");
		return false;
	}
	if(nome.length>30){
		avviso("nome","La lunghezza massima del nome è 30");
		return false;
	}else{
		return true;
	}
}
function controllaPrezzo(prezzo){
	prezzo=prezzo.replace(".","");
	var prezzoV=/^[0-9]+$/;
	if(String(prezzo).match(prezzoV))
	{
		return true;
	}
	avviso("prezzo","Il prezzo deve contenere solo numeri");
	return false;
}
function controlloQuantita(quantita){
	if (quantita<0){
		avviso("quantita","La quantita deve essere positiva");
		return false;
	}
	var quantitaV=/^[0-9]+$/;
	if(String(quantita).match(quantitaV))
	{
		return true;
	}
	avviso("quantita","La quantità non rispetta i canoni prestabiliti");
	return false;
}
function validaId(id){
	if(id.length>10){
		avviso("id","La lunghezza deve essere inferiore a 10");
		return false;
	}
}
function avviso(id,mex){
	$("#"+id).html(mex);
	$("#"+id).fadeIn("slow");
	$("#"+id).delay(1000).fadeOut("slow");
}
