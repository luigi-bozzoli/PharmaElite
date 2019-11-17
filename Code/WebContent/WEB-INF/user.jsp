<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.beans.ClienteBean, model.beans.DatiAnagraficiBean,model.beans.IndirizzoSpedizioneBean,model.beans.TelefonoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<link href="stylesheets/headerFooter.css" rel="stylesheet" />
<link href="stylesheets/User.css" rel="stylesheet" />

<title>Pharmaélite</title>
</head>

<body>


	<%
		ClienteBean cliente = (ClienteBean) request.getAttribute("cliente");
		DatiAnagraficiBean dati = (DatiAnagraficiBean) request.getAttribute("datiAnagrafici");
		TelefonoBean telefono = (TelefonoBean) request.getAttribute("telefono");
		IndirizzoSpedizioneBean indirizzo = (IndirizzoSpedizioneBean) request.getAttribute("indirizzo");
	%>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse-main">
					<span class="sr-only"> Toggle navigation </span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a id="logo" class="navbar-brand"><img id="immagineLogo"
					class="img-rounded" src="Immagini/logo.PNG" alt="logo"></a>
			</div>

			<div class="collapse navbar-collapse" id="navbar-collapse-main">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="vociMenu" href="home.html"> <i
							class="fas fa-home"></i>
					</a></li>
					<li><a class="vociMenu" href="UserPage"> <i
							class="fas fa-user"></i>
					</a></li>
					<li><a class="vociMenu" href="Carrello"> <i
							class="fas fa-shopping-cart"></i>
					</a></li>
				</ul>

				<form id="form" class="navbar-form navbar-left"
					action="/action_page.php">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search"
							name="search">
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>

			</div>
		</div>

	</nav>


	<div class="container text-center main">

		<div id="topDiv" class="row">

			<div id="profileImage">
				<%
					if (dati.getSesso().equalsIgnoreCase("uomo")) {
				%>
				<img src="Immagini/avatarUomo.png" alt="Avatar" class="avatar">
				<%
					} else {
				%>
				<img src="Immagini/avatarDonna.png" alt="Avatar" class="avatar">
				<%
					}
				%>
			</div>
			<div>
				<h3><%=dati.getNome()%>
					<%=dati.getCognome()%></h3>
			</div>
		</div>

		<div id="firstRow" class="row">
			<div class="col-sm-6">
				<h4>Indirizzo di spedizione</h4>
				<p>
					<%=indirizzo.getIndirizzo()%></p>
			</div>



			<div class="col-sm-6">
				<h4>Telefono</h4>
				<p><%=telefono.getNumero()%></p>
			</div>
		</div>

		<div id="secondRow" class="row">
			<div class="col-sm-6">
				<h4>Email</h4>
				<p>
					<%=dati.getEmailCliente()%></p>
			</div>
			<div class="col-sm-6">


				<h4>Città</h4>
				<p>
					<%=dati.getCittà()%></p>
			</div>

		</div>

		<div class="row text-center">
			<a href="Logout" role="button" class="btn btn-success btn-block">Logout</a>
		</div>

		<div style="padding-top: 30px;" class="row text-center">
		<a href="ListaOrdini" role="button" class="btn btn-success btn-block">Ordini</a>
			<%
				if (cliente.getTipo().equalsIgnoreCase("admin")) {
			%>
			<a href="AggiungiProdotto" role="button" class="btn btn-success btn-block">Aggiungi
				prodotto</a> 
				<button id="eliminaProdotto" class="btn btn-success btn-block">Elimina Prodotto </button>
				<a href="Modifica" role="button" class="btn btn-success btn-block">Modifica
				prodotto</a>
			<%
				}
			%>
		</div>
		<form method="get" action ="EliminaProdotto">
		<div id="buttonDiv"  class="row text-center">
			<div id="barraRicerca" class="text-center">
				<div style="border: none;" class="row">
					<div class="col-sm-6">
						<input type="text" name="id" class="form-control" placeholder="ID">
					</div>
				
					<div class="col-sm-6">
						<button id="confermaEliminazione" class="btn btn-success"> Elimina </button>
					</div>
				</div>
			</div>
		</div>
		</form>



	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="Script/UserPage.js"> </script>

</body>
</html>

