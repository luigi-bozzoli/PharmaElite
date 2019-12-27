<%@page import="java.util.Iterator"%>
<%@page import="managerCatalogo.ProdottoBean"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
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
<style media="screen">
	body{
	padding-top: 100px;
}
</style>
</head>

<body>
<%
Set<ProdottoBean> listaProdotti = (Set<ProdottoBean>) request.getAttribute("listaProdotti");
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
					<th style="width: 60%">Prodotto</th>
					<th style="width: 13%">Prezzo</th>
					<th class="text-center" style="width: 13%">Quantità</th>
					<th style="width: 13%"></th>
				</tr>
			</thead>

			<tbody>
				<%if(listaProdotti.size() == 0){ %>	
				<tr><td><h2>Nessun prodotto trovato</h2></td></tr>
			<%} %>
			
			<% 
			Iterator<ProdottoBean> i = listaProdotti.iterator();
			while(i.hasNext()){
				ProdottoBean p = i.next();
				if(!p.isFlagEliminato()){
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
								<h4 class="nomargin"><%=p.getNome()%></h4>
								<p><%=p.getDescrizione() %></p>
							</div>
						</div>
					</td>
					<td data-th="Prezzo"><%=p.getPrezzo() %></td>
					<td data-th="Quantità">
						<p class="form-control text-center"><%=p.getQuantita() %></p>
						</td>
					<td class="text-center actions">
					<button class="carrello btn btn-danger btn-sm">
							<i class="fas fa-trash-alt"></i>
					</button>
					</td>
				</tr>

<%}} %>






			</tbody>



			<tfoot>
				<tr class = "text-center">
					<td colspan="5"><a href="home.html" class="btn btn-success"> Userpage</a></td>
				</tr>
			</tfoot>
		</table>
	</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="Script/listaProdotti.js"></script>
<script src ="Script/aggiungiAlCarrello.js"></script>
</body>
</html>
    