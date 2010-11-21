<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.*"%>
<jsp:useBean id="piso" scope="session" class="controladores.beans.EdificioBean"/>
<script type="text/javascript">
function cambiar(indice){
	var idDispositivo = document.getElementById("idDispositivo");
	var x = document.getElementById("accion"+indice).value;
	var accion =document.getElementById("accionEjecutar");
	accion.value=x;	
	idDispositivo.value=indice;	
}
</script>
<% List<Dispositivo> dispositivos = piso.obtenerListadoDeDispositivos(); %>
<div class="contenido">
	<div class="titulo"><h3>Dispositivos</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Listado de Dispositivos </legend>
				<form class="elegante" id="dispositivoListado" name="dispositivoListado" action="ListadorDeDispositivos!ejecutar">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Dispisitivo</td>
								<td class="listado_par">Estado</td>
								<td class="listado_par">Ejecutar</td>									
							</tr>	
						<%	
							int index = 0 ;
							for (Dispositivo dispositivo:dispositivos) {
								
						%>		
							<tr>
								<td><%=dispositivo.obtenerDescripcion()%></td>
								<%String estadoActual = dispositivo.isEncendido()?"Encendido":"Apagado";%>
								<td><%=estadoActual %></td>
								<td><select  id="accion<%=index%>"><option value="encender">Encender</option><option value="apagar">Apagar</option></select><input type="submit" value="cambiar" onclick="cambiar('<%=index%>')"></input></td>																		
							</tr>	
						<%index++;
						} %>										
						</table>
						<input type="hidden" value="<%=piso.obtenerNivel()%>" name="nivel">
						<input type="hidden" name="idDispositivo" id="idDispositivo"></input>
						<input type="hidden" name="accionEjecutar" id="accionEjecutar"></input>
					</form> 
				</fieldset>
				<a href="ListadorDeDispositivos!seleccionarPisos">Volver</a>
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>