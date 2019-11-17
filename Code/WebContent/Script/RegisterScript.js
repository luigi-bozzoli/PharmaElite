var bNome=false;
var bCognome = false;
var bCarta = false;
var bIndirizzo = false;
var bCitta = false;
var btelefono = false;
var bemail = false;
var bpassword = false;

function validaForm(){
	verificaNome();



	if(bemail){
	
		bemail = controlloEsistenza(emailUtente);
	}




	var temp=$("input[name=check]").is(":checked");
	avvisaPrivacy(temp);


	if(bNome && bCognome && bCarta && bIndirizzo && bCitta && btelefono && bemail && bpassword && temp){

		return true;
	}else{

		window.scrollTo(0,0);
		return false;}
}

function controlloEsistenza(email) {
	

	var email=$("input[name=email]").val();

	queryString = "?email="+email;
	urlRequest = "Register"+queryString;

	$.ajax({
		url: urlRequest, 
		async: false,
		success: function(data){
			if(data == "true"){
				bemail =  true;
			}else{
				avviso("mail", "Email già in uso");
				bemail =false;
				return;
			}
		}
	})

}

function avvisaPrivacy(boolean){
	if(!boolean){
		avviso("check", "Devi acconsentire al trattamento dei tuoi dati personali");
	}
}

function avviso(id,mex){
	$("#"+id).html(mex);
	$("#"+id).fadeIn("slow");
	$("#"+id).delay(1000).fadeOut("slow");
}

function verificaNome()
{

	var name=$("input[name=nome]").val();
	if(name.length<1 || name.length>30){
		avviso("nome","La lunghezza deve essere compresa fra 1 e 30");
		bNome =  false;
		return;
	}
	var nameV=/^[a-zA-Z]+$/;
	if(String(name).match(nameV))
	{
		bNome= true;
	}
	else
	{
		avviso("nome","Il nome deve contenere solo caratteri");
		bNome= false;
	}
}


function verificaCognome()
{
	var cognome=$("input[name=cognome]").val();
	
	if(cognome.length<1 || cognome.length>30){
		avviso("cognome","La lunghezza del cognome deve essere compresa fra 1 e 30");
		bCognome= false;
		return;
	}
	var cognomeV=/^[a-zA-Z]+$/;
	if(String(cognome).match(cognomeV))
	{
		bCognome= true;
	}
	else
	{
		avviso("cognome","Il cognome deve contenere solo caratteri");
		bCognome= false;
	}
}

function verificaCarta()
{

	var numeroCarta=$("input[name=numCarta]").val();
	
	if(numeroCarta.length!=16){
		avviso("numeroCarta","La lunghezza del numero deve essere 16");
		bCarta= false;
		return;
	}
	var cartaV=/^[0-9]+$/;
	if(String(numeroCarta).match(cartaV))
	{
		bCarta= true;
	}
	else
	{
		avviso("numeroCarta","Il nome deve contenere solo numeri");
		bCarta= false;
	}
}


function verificaIndirizzo()
{
	var indirizzo=$("input[name=indSped]").val();
	if(indirizzo.length==0){
		avviso("ind","Il campo è obbligatorio");
		bIndirizzo = false;
		return;
	}
	if(indirizzo.length > 50){
		avviso("ind","L'indirizzo non può superare i 50 caratteri");
		bIndirizzo= false;
	}

	bIndirizzo= true;
}

function verificaCitta(){

	var citta = $("input[name=citta]").val();
	if(citta.length<1 || citta.length>30){
		avviso("citta","La lunghezza deve essere compresa fra 1 e 30");
		bCitta =  false;
		return;
	}
	var cittaV=/^[a-zA-Z]+$/;
	if(String(citta).match(cittaV))
	{
		bCitta =  true;
	}
	else
	{
		avviso("citta","La città deve contenere solo caratteri");
		bCitta= false;
	}
}

function verificaTelefono()
{
	var telefono=$("input[name=tel]").val();
	if(telefono.length!=10){
		avviso("tel","Il numero deve essere composto da 10 cifre");
		btelefono= false;
		return;
	}
	var telefonoV=/^[0-9]+$/;
	if(String(telefono).match(telefonoV))
	{
		btelefono= true;
	}
	else
	{
		avviso("tel","Il telefono può contenere solo numeri");
		btelefono= false;
	}
}

function verificaEmail(){
	var email=$("input[name=email]").val();
	if(email.length==0){
		avviso("mail","Il campo è obbligatorio");
		bemail= false;
		return;
	}
	var emailV=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/;
	if(String(email).match(emailV))
	{
		bemail= true;
	}
	else
	{
		avviso("mail","La mail non rispetta i canoni");
		bemail= false;
	}
}

function verificaPassword()
{

	var pass=$("input[name=password]").val();
	if(pass.length==0){
		avviso("pwd","Il campo non può essere nullo");
		bpassword= false;
		return;
	}
	if(pass.length > 20){
		avviso("pwd","La lunghezza deve essere inferiore a 20");
		bpassword= false;
		return;
	}
	var passv=/^[0-9a-zA-Z]+$/;
	if(String(pass).match(passv))
	{
		bpassword=true;
	}
	else
	{
		avviso("pwd","La password non rispetta i canoni");
		bpassword=false;
	}
}
