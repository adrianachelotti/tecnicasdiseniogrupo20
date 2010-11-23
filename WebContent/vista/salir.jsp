<%@ taglib uri="/struts-tags" prefix="s" %>  
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Edificio Inteligente</title>
	    <link rel="stylesheet" href="<s:url value='menu_style.css'/>" type="text/css" />
</head>
<body>
<div id="contenedor">
<div >
<ul class="menu"> 
<li class="top">
<a href="autenticacion.jsp" class="top_link"><span>Login</span></a></li>
</ul>
</div>
<table id="tablaLogin" height ="200" cellpadding="0" cellspacing="0">
		<tr><td height="5" bgcolor="#383838" colspan="3"></td></tr>
		<tr>
			<td width="5"  bgcolor="#383838"></td>
			<td width="800" bgcolor="#F0F0F0" align="center">
					<font>Gracias por usar nuestros servicios</font>					
					
			</td>	  
			<td width="5"  bgcolor="#383838"></td>
		</tr>
		<tr><td height="5" bgcolor="#383838" colspan="3"></td></tr>
	</table>
	<s:actionerror cssClass="error"/>

<jsp:include page="piedepagina.jsp"></jsp:include>