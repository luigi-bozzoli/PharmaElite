function aggiungiAlCarrello(){
	//quantità
	var x = $(this).parent().siblings().find("input").val();
	
	if(x==0)
		return;
	//id prodotto
	var y = $(this).prev().val();

	richiesta(x,y, this);


	avviso(this);
}

function richiesta(x, y, button){
	var quantità = "quantità="+x+"&";
	var id = "id="+y;
	var qString = "?"+ quantità + id;
	var url = "AggiungiAlCarrello"+qString;


	$.get(url, function(data, status){
		
		if(data == "true"){
			$(button).parent().siblings().find("h3").html("Prodotto aggiunto al carrello");
		}else{
			$(button).parent().siblings().find("h3").html("Sono disponibili solo "+data+" unità");
		}
  });
}

function avviso(button){
	$(button).parent().siblings().find("h3").fadeIn("slow");
	$(button).parent().siblings().find("h3").delay(1000).fadeOut("slow");
}

$(document).ready(function(){
  $(".carrello").click(aggiungiAlCarrello);
})
