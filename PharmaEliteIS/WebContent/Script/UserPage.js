function cambiaTesto(testo) {
	$("h5").html(testo);
}

function mostraBarra(){
	$("#barraRicerca").fadeIn();
}

$(document).ready(function(){
	$("#eliminaProdotto").click(mostraBarra);
	$("#confermaEliminazione").click(eliminaProdotto);

})