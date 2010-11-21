<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.*"%>
<%@ page language="java" contentType="text/html" import="modelo.manejadorDeSucesos.*"%>
<jsp:useBean id="piso" scope="session" class="controladores.beans.EdificioBean"/>
<script type="text/javascript">
function cambiar(indice){
	var idSensor = document.getElementById("idSensor");
	var x = document.getElementById("accion"+indice).value;
	var accion =document.getElementById("medicionCambiar");
	accion.value=x;	
	idSensor.value=indice;	
}
</script>
<% List<Implicacion> reglas = piso.obtenerListadoDeReglas(); %>
<div class="contenido">
	<div class="titulo"><h3>Reglas</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Listado de Reglas </legend>
				<form class="elegante" id="sensorListado" name="sensorListado" action="ListadorDeReglas!cambiarMedicion">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Reglas</td>
								<td class="listado_par">Eventos Esperados</td>
								<td class="listado_par">Dispositivo</td>									
								<td class="listado_par">Accion</td>									
							</tr>	
						<%	
							int index = 0 ;
							for (Implicacion reglaActual:reglas) {								
						%>		
							<tr>
								<td><%=reglaActual.getIdentificador() %></td>								
								<td><%for (Suceso suc: reglaActual.getSucesos()){%>
										<%=suc.getIdSuceso() %></br>
									<%} %>
								</td>
								<td>
									<%=reglaActual.getAccion().obtenerDispositivo().obtenerDescripcion() %>
								</td>
								<td> 
									<%--String accion = reglaActual.getClass().getName().equalsIgnoreCase("AccionApagarDispositivo")? "Apagar":"Encender";--%>
									<%--=accion--%>
									Encender
								</td>							
							</tr>	
						<%index++;
						} %>										
						</table>
						<input type="hidden" value="<%=piso.obtenerNivel()%>" name="nivel">
						<input type="hidden" name="idSensor" id="idSensor"></input>
						<input type="hidden" name="medicionCambiar" id="medicionCambiar"></input>
					</form> 
				</fieldset>
				<a href="ListadorDeReglas!seleccionarPisos">Volver</a>
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>