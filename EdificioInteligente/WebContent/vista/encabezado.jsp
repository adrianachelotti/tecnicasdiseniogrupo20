<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Edificio Inteligente</title>
    <link rel="stylesheet" href="<s:url value='menu_style.css'/>" type="text/css" />
</head>
<body>
<div id="contenedor">
<div>
<ul class="menu">
	<li class="top"><a href="principal.jsp" class="top_link"><span>Home</span></a></li>

	<li class="top"><a href="#" class="top_link"><span>Edificio</span></a>
		<ul class="sub">
			 <li><a href="ListadorDePisos" >Visualizar Pisos</a></li>			 
		</ul>
	</li>

	<li class="top"><a href="#" class="top_link"><span>Dispositivos</span></a>
		<ul class="sub">
			<li><a href="ListadorDeDispositivos!seleccionarPisos">Ver Dispositivos</a></li>					            
		</ul>
	</li>
	<li class="top"><a href="#" class="top_link"><span>Sensores</span></a>
		<ul class="sub">
			<li><a href="ListadorDeSensores!seleccionarPisos">Ver Sensores</a></li>			                 
		</ul>
	</li>
	<li class="top"><a href="#" class="top_link"><span>Reglas</span></a>
		<ul class="sub">
			<li><a href="ListadorDeReglas!seleccionarPisos">Configurar</a></li>			
		</ul>
	</li>
	
	<li class="top"><a href="salir.jsp" class="top_link"><span>Salir</span></a></li>	
</ul>
</div>
<div class="clear-both"></div>



