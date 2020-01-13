/**
 * 
 */
var x = false;

function validateForm() {
	if(x){
		return true;
	}

	var email =$("#email").val();
	var pass = $("input[name=password]").val();
	var qString = "?email="+email + "&password="+pass;
	var url = "ControlloLogin"+qString;


	$.ajax({
		url: url, 
		async: false,
		success: function(data){
			if(data == "true"){
				x = true;
				$("#login").submit();
				return true;
				//avviso utente
			}else{

				avvisa("Errore");
			}
		}
	});
	return false;
}


function avvisa(x){
	$("h3").text(x);
	$("h3").fadeIn();
	$("h3").delay(1000).fadeOut();
}
