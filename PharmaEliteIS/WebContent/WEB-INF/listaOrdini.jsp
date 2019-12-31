<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="managerOrdine.OrdineBean"%>
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


<link href="stylesheets/headerFooter.css" rel="stylesheet" />
<link href="stylesheets/listaOrdini.css" rel="stylesheet" />

<title>Storico ordini</title>
</head>

<body>

	<%
	Set<OrdineBean> lista = (Set<OrdineBean>) request.getAttribute("lista");
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
    <a id="logo" class="navbar-brand"><img id="immagineLogo" class="img-rounded"
      src="Immagini/logo.PNG" alt="logo"></a>
  </div>

  <div class="collapse navbar-collapse" id="navbar-collapse-main">
    <ul class="nav navbar-nav navbar-right">
      <li><a class="vociMenu" href="home.html"> <i
          class="fas fa-home"></i>
      </a></li>
      <li><a class="vociMenu" href="Userpage"> <i class="fas fa-user"></i>
      </a></li>
      <li><a class="vociMenu" href="Carrello"> <i
          class="fas fa-shopping-cart"></i>
      </a></li>
    </ul>

    <form id="form" class="navbar-form "
      action="CercaProdotto">
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


		<table id="main" class="table table-hover table-condensed">

			<%if (lista == null || lista.size() == 0){ %>
			<tbody>
				<tr>
					<td><h2 class="text-center">Nessun ordine effettuato</h2></td>
				</tr>
			</tbody>

			<tfoot>
				<tr>
					<td class="text-center"><a href="home.html"
						class="btn btn-warning"> <i class="fa fa-angle-left"></i>
							Continua gli acquisti
					</a></td>
				</tr>
			</tfoot>
			<%}else{
		%>
			<thead>
				<tr>
					<th style="width: 50%">Prodotti</th>
					<th style="width: 10%">Costo</th>
					<th style="width: 8%">Data ordine</th>
					<th style="width: 22%" class="text-center">ID Ordine</th>
					<th style="width: 10%">Stato</th>
				</tr>
			</thead>

			<tbody>

				<%
				Iterator<OrdineBean> i = lista.iterator();
				while(i.hasNext()){
					OrdineBean ordine = i.next();
						%>
				<tr>
					<td data-th="Prodotti">
						<div class="row">
							<div class="col-sm-12">
								<%	Iterator<String> iString = ordine.getListaProdotti().iterator();
										while(iString.hasNext())
								{ %>
										<h6 class="nomargin"><%=iString.next()%></h6>
								<%} %>
							</div>
						</div>
					</td>

					<td data-th="Costo">EUR <%=ordine.getCosto() %></td>
					<td data-th="Data ordine"><%=ordine.getDataOrdine()%></td>
					<td data-th="ID ordine" class="text-center"><%=ordine.getId() %></td>
					<td>
						<div id="successDiv">
							<label class="label label-success">Approvato</label>
						</div>
					</td>
				</tr>
			</tbody>

		<%} }%>

		</table>


	</div>




</body>
</html>
