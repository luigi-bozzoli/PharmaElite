<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.DatiAnagraficiBean"%>

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


<link href="stylesheets/registerSuccess.css" rel="stylesheet"/>

<title>Pharmaélite</title>
</head>
<body>
<%
DatiAnagraficiBean dati = (DatiAnagraficiBean) request.getAttribute("datiAnagrafici");
%>

  <div class="container">
  	<div id="mainDiv" class="row text-center">
          <div class="col-sm-6 col-sm-offset-3">
          <br><br> <h2 class="text-success">Success</h2>
          <h3><%= dati.getNome()%> <%= dati.getCognome() %></h3>
          <p class="text-muted" style="font-size:20px;">
            Grazie per la registrazione. Effettua l'accesso per usufruire dei servizi. </p>
          <a href="Login.html" class="btn btn-success"> Login</a>
          </div>

  	</div>
  </div>
</body>
</html>
