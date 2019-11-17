<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.beans.ProdottoBean" %>
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
<link href="stylesheets/carrello.css" rel="stylesheet" />

<title>Pharmaélite</title>
</head>

<body>
<%
ArrayList<ProdottoBean> listaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("listaProdotti");
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
					action="Cerca">
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

	<div class="container">
		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 50%">Prodotto</th>
					<th style="width: 10%">Prezzo</th>
					<th style="width: 8%">Quantità</th>
					<th style="width: 22%" class="text-center">Prezzo Totale</th>
					<th style="width: 10%"></th>
				</tr>
			</thead>

			<tbody>
			
			<%if(listaProdotti.size() == 0){ %>	
				<tr><td><h2>Nessun prodotto trovato</h2></td></tr>
			<%} %>
			
			<% for (int i = 0; i < listaProdotti.size(); i++){ 
				ProdottoBean p = listaProdotti.get(i);
			%>
				<tr>
					<td data-th="Prodotto">
						<div class="row">
							<div class="col-sm-2 hidden-xs">
								<img
									src="<%= p.getUrlImmagine() %>"
									alt="immagine prodotto" class="img-responsive" />
							</div>
							<div class="col-sm-10">
								<h4 class="nomargin"><%= p.getNome() %></h4>
								<p><%=p.getDescrizione() %></p>
								<h3 class="text-center" style="color:green; display: none;"> Prodotto aggiunto al carrello</h3>
							</div>
						</div>
					</td>
					<td data-th="Prezzo">€<%=p.getPrezzo() %></td>
					<td data-th="Quantità">
						<input type="number" class="form-control text-center" min ="0" max="15" value="1" onclick="aggiornaPrezzo(this)">
					</td>
					<td data-th="Prezzo totale" class="text-center">€<%=p.getPrezzo() %></td>
					<td class="actions">
					<input type = "hidden" value="<%=p.getId()%>">
					<button class="carrello btn btn-success btn-sm">
							<i class="fas fa-shopping-cart"></i>
					</button>
					</td>
				</tr>
				<%} %>







			</tbody>



			<tfoot>
				<tr>
					<td colspan="4"><a href="home.html" class="btn btn-warning"><i
							class="fa fa-angle-left"></i> Categorie</a></td>
					<td><a href="Carrello" class="btn btn-success">Carrello <i
							class="fa fa-angle-right"></i></a></td>
				</tr>
			</tfoot>
		</table>
	</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="Script/listaProdotti.js"></script>
<script src ="Script/aggiungiAlCarrello.js"></script>
</body>
</html>
