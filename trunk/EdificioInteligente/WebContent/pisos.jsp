<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.Piso"%>
<jsp:useBean id="edificio" scope="session" class="controladores.beans.EdificioBean"/>
<%
	List<Piso> listadoDePisos = edificio.obtenerListadoDePisos();
%>
<div class="contenido">
	<div class="titulo"><h3>Pisos</h3></div>
	<div class="cuerpo" align="center">
		<table width ="500" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="500" align="center">
			<fieldset> <legend>Listado de Pisos </legend>
				<form class="elegante" id="pisosListado" name="pisosListado" action="">
						<table width="400" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Nivel</td>
								<td class="listado_par">&nbsp;</td>	
								<td class="listado_par">&nbsp;</td>																								
							</tr>	
						<%	
							for (Piso piso:listadoDePisos) {						
						%>		
							<tr>
								<td><%=piso.getNivel() %></td>
								<td><a href="AgregarDispositivo?nivel=<%=piso.getNivel()%>">Agregar Dispositivos</a></td>																		
								<td><a href="AgregarSensor?nivel=<%=piso.getNivel()%>">Agregar Sensores</a></td>																		
							</tr>	
						<%} %>										
						</table>
					</form> 
				</fieldset>
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>