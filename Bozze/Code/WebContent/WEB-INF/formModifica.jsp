<%@page import="model.beans.ProdottoBean"%>
<%@ page language="java"%>
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

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>


<link href="stylesheets/registerStyle.css" rel="stylesheet" />
<link href="stylesheets/registerLogin.css" rel="stylesheet" />

<link href="stylesheets/headerFooter.css" rel="stylesheet" />

<title>Pharmaélite</title>
</head>

<body>
	<%
		ProdottoBean p = (ProdottoBean) request.getAttribute("prodotto");
		String categoria = p.getCategoria();
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
					<li><a class="vociMenu" href="#"> <i
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

	<!-- form registrazione -->

	<div class="wrapper">

		<form class="form-signin" action="SalvaModifiche" method="POST" onsubmit="return validateForm()">
			<h2 class="form-signin-heading text-center">Modifica Prodotto</h2>
			
			<input type="hidden" name = "id" value="<%=p.getId() %>">
			<input type="hidden" name="url" value="<%=p.getUrlImmagine()%>">
			

			<h4> ID Prodotto </h4><h4><%=p.getId()%></h4>
			<h4> Nome Prodotto</h4>
			
			<input type="text" name="nomeProdotto" class="form-control" value="<%=p.getNome()%>">
				<h3 id="nomeProdotto"></h3>
				
				<h4> Categoria</h4>
				
				<div style="margin-bottom: 20px;">
				<select name="categoria"  class=" form-control selectpicker" >
	  <option <%if(categoria.equalsIgnoreCase("Erboristeria")){ %>selected="selected"<%}%> >Erboristeria</option>
	  <option <%if(categoria.equalsIgnoreCase("Integratori")){ %>selected="selected"<%}%>>Integratori</option>
	  <option <%if(categoria.equalsIgnoreCase("Farmaci da banco")){ %>selected="selected"<%}%>>Farmaci da banco</option>
	  <option <%if(categoria.equalsIgnoreCase("Igiene Orale")){ %>selected="selected"<%}%>>Igiene orale</option>
	</select>
	</div>
				
				<h4> Prezzo</h4>
				<input type="text" name="prezzo" class="form-control" value="<%= p.getPrezzo()%>">
				<h3 id="price"></h3>
				
				<h4> Quantità</h4>
<input type="text" name="quantita" class="form-control" value="<%= p.getQuantità()%>">
<h3 id="quantita"></h3>

<h4> Descrizione</h4>
<textarea rows="4" cols="50" name="descrizione"
				class="form-control"> <%= p.getDescrizione()%>
				</textarea>
				<h3 id="descrizione"></h3>



<button type="submit" class="btn btn-lg btn-primary btn-block">Salva Modifiche</button>


		</form>




	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="Script/formModifica.js"></script>
</body>
</html>
