<%@page import="model.beans.IndirizzoSpedizioneBean"%>
<%@page import="model.beans.MetodoDiPagamentoBean"%>
<%@page import="model.beans.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
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

<link href="stylesheets/headerFooter.css" rel="stylesheet" />
<link href="stylesheets/Checkout.css" rel="stylesheet" />

<title>Checkout</title>
</head>

<body>

	<%
		double prezzoTot = (double) request.getAttribute("prezzoTot");
		ArrayList<String> listaProd = (ArrayList<String>) request.getAttribute("listaProdotti");
		ArrayList<MetodoDiPagamentoBean> listaCarte = (ArrayList<MetodoDiPagamentoBean>) request
				.getAttribute("listaCarte");
		ArrayList<IndirizzoSpedizioneBean> listaInd = (ArrayList<IndirizzoSpedizioneBean>) request
				.getAttribute("listaInd");
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

				<form id="form" class="navbar-form navbar-left" action="Cerca">
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

	<form id="metodoPagamento" action="AggiungiMetodoPagamento"
		method="get" onsubmit="return checkCarta()"></form>
	<form id="indirizzoSped" action="AggiungiIndirizzoSpedizione"
		method="get" onsubmit="return checkIndirizzo()"></form>

	<div class='container'>
		<div class="col-md-8 col-md-offset-2">

			<a href="home.html" class="btn btn-info btn-block">Aggiungi
				prodotti</a>
			<hr />

			<form id="checkout" class="form-horizontal" role="form"
				action="ProcediAllOrdine">
				<div class="shopping_cart">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span>Il tuo ordine</span>
							</h4>
						</div>
						<div class="panel-body">
							<div class="items rom">
								<div class="col-sm-9">
									<div class="row">
										<div class="col-sm-9">
											<select onchange="aggiornaPrezzo()" name="spedizione"
												class="selectpicker">
												<option value="gratuita">Gratuita (entro 5 giorni
													lavorativi)</option>
												<option value="standard">Standard (entro 3 giorni
													lavorativi)</option>
												<option value="rapida">Rapida (entro 24 ore)</option>
											</select>
										</div>
										<div style="padding-top: 20px;" class="col-sm-3">
											<h5>Prezzo Spedizione</h5>
											<h5 id="prezzoSped">EUR 0</h5>
										</div>
									</div>
									<hr />
									<div class="row">
										<div class="col-sm-9">
											<ul>
												<%
													for (int i = 0; i < listaProd.size(); i++) {
												%>
												<li><%=listaProd.get(i)%></li>
												<%
													}
												%>
											</ul>
										</div>
										<div class="col-sm-3">
											<span id="prezzoProdotti"> EUR <%=prezzoTot%></span>
										</div>
									</div>
								</div>


								<div class="col-sm-3">
									<div>
										<h3>Totale Ordine</h3>
										<h3>
											<span id="totOrdine">EUR <%=prezzoTot%></span>
										</h3>
									</div>
								</div>
							</div>
						</div>


					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span>Metodo di pagamento</span>
							</h4>
						</div>
						<div>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-6">
										<select name="tipoCarta" class="selectpicker">
											<%
												for (int i = 0; i < listaCarte.size(); i++) {
											%>
											<option value="<%=listaCarte.get(i).getNumCarta()%>">
												<%=listaCarte.get(i).getTipo()%>-<%=listaCarte.get(i).getNumCarta()%></option>
											<%
												}
											%>
										</select>
									</div>
									<div class="col-sm-6">
										<button type="button" onclick="mostraDiv('.metodoPagamento')"
											class="btn btn-success">Aggiungi metodo di pagamento</button>
									</div>
								</div>

								<!-- AGGIUNGI METOTODO PAGAMENTO -->
								<div class="metodoPagamento row">

									<div class="col-sm-5">
										<select form="metodoPagamento" name="tipoCarta"
											class="selectpicker">
											<option value="MasterCard">MasterCard</option>
											<option value="Visa">Visa</option>
											<option value="American Express">American Express</option>
										</select>
									</div>
									<div class="col-sm-5">
										<input form="metodoPagamento" class="form-control" type="text"
											name="numCarta" placeholder="Numero carta">
									</div>
									<div class="col-sm-2">
										<button form="metodoPagamento" type="submit"
											class="btn btn-success">Salva</button>
									</div>



								</div>
								<h3 id="numeroCarta">La carta non rispetta i parametri</h3>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span>Indirizzo di spedizione</span>
							</h4>
						</div>
						<div>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-6">
										<select name="indirizzi" class="selectpicker">
											<%
												for (int i = 0; i < listaInd.size(); i++) {
											%>
											<option value="<%=listaInd.get(i).getIndirizzo()%>">
												<%=listaInd.get(i).getIndirizzo()%></option>
											<%
												}
											%>
										</select>
									</div>
									<div class="col-sm-6">
										<button type="button"
											onclick="mostraDiv('.indirizzoSpedizione')"
											class="btn btn-success">Aggiungi indirizzo</button>
									</div>
								</div>


								<div class=" indirizzoSpedizione row">

									<div class="col-sm-6">
										<input form="indirizzoSped" class="form-control" type="text"
											name="indirizzoSped" placeholder="Indirizzo di spedizione">
									</div>

									<div class="col-sm-6">
										<button form="indirizzoSped" type="submit"
											class="btn btn-success">Salva</button>
									</div>
								</div>
								<h3 id="ind">L'indirizzo non rispetta i canoni</h3>
							</div>
						</div>
					</div>

					<button onclick="prova()" form="checkout" type="submit"
						class="btn btn-success btn-block">Checkout</button>

				</div>
			</form>




		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="Script/CheckoutScript.js">
		
	</script>
	<script src="RegisterScript.js">
		
	</script>
</body>
</html>
